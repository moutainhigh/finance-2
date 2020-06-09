package com.july.company.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.july.company.constant.SystemConstant;
import com.july.company.dto.login.*;
import com.july.company.dto.role.UserRoleDto;
import com.july.company.dto.sms.SmsCodeDto;
import com.july.company.dto.sms.SmsCodeVerifyDto;
import com.july.company.dto.user.*;
import com.july.company.entity.Company;
import com.july.company.entity.UserInfo;
import com.july.company.entity.enums.SmsCodeEnum;
import com.july.company.exception.BnException;
import com.july.company.intercepts.TokenHandle;
import com.july.company.mapper.UserInfoMapper;
import com.july.company.service.CompanyService;
import com.july.company.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.service.UserRoleService;
import com.july.company.utils.Md5Utils;
import com.july.company.utils.UUIDUtils;
import com.july.company.utils.UserUtils;
import com.july.company.vo.login.UserInfoValidVo;
import com.july.company.vo.sms.SmsCodeVo;
import com.july.company.vo.user.SelectUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 用户服务
 * @author zengxueqi
 * @since 2020-05-16
 */
@Service
@Slf4j
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Value("${aliyun.sms.regionId}")
    private String regionId;
    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.sms.secret}")
    private String secret;
    @Value("${aliyun.sms.siginName}")
    private String siginName;
    @Value("${aliyun.sms.templateCode}")
    private String templateCode;
    @Resource
    ValueOperations<String, Object> valueOperations;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private TokenHandle tokenHandle;
    @Resource
    private CompanyService companyService;
    @Resource
    private UserRoleService userRoleService;

    /**
     * 用户登录信息
     * @param loginAuthDto
     * @return com.july.company.dto.user.UserInfoDto
     * @author zengxueqi
     * @since 2020/6/9
     */
    @Override
    public UserInfoDto login(LoginAuthDto loginAuthDto) {
        String mobile = loginAuthDto.getMobile();
        log.trace("用户手机:{},登录系统", loginAuthDto.getMobile());
        //数据库匹配用户
        UserInfo userInfo = this.getUserInfoByMobile(loginAuthDto.getMobile());

        if (SystemConstant.SYS_TRUE.equals(loginAuthDto.getLoginType())) {
            List<UserRoleDto> userRoleDtos = userRoleService.getUserRole(userInfo.getId());
            BnException.of(CollectionUtils.isEmpty(userRoleDtos), "你不是系统管理员，无法登录后台！");
        }

        BnException.of(userInfo == null, "用户不存在");
        BnException.of(SystemConstant.SYS_TRUE.equals(userInfo.getStatus()), "当前账号已经被禁用，请联系管理员！");

        UserInfo checkUserInfo = this.getUserInfoByMobileAndPassword(mobile, loginAuthDto.getEncryptPassword(userInfo.getPwdSalt()));
        if (checkUserInfo == null) {
            throw BnException.on("用户名或密码错误");
        }
        if (checkUserInfo.getDeleted() == 1) {
            throw BnException.on("账号已被删除");
        }
        String token = UUIDUtils.getReplaceUuid();
        String tokenDes = tokenHandle.encryptAuth(token, loginAuthDto.getLoginType(), checkUserInfo.getId());

        Company company = companyService.getById(checkUserInfo.getCompanyId());
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .userName(checkUserInfo.getUsername())
                .avatar(checkUserInfo.getAvatar())
                .mobile(checkUserInfo.getMobile())
                .id(checkUserInfo.getId())
                .token(tokenDes)
                .loginType(loginAuthDto.getLoginType())
                .companyName(company != null ? company.getCompanyName() : "")
                .build();

        //格式为：login_1_948B2032A6F34492B5F0633E1D441F27
        valueOperations.set(SystemConstant.CACHE_NAME + loginAuthDto.getLoginType() + "_" + token, userInfoDto.getId(), SystemConstant.EXPIRE_LOGIN, TimeUnit.MINUTES);
        updateCache(userInfoDto, loginAuthDto.getLoginType());
        return userInfoDto;
    }

    /**
     * 更新缓存信息
     * @param userInfoDto
     * @return void
     * @author zengxueqi
     * @since 2020/5/27
     */
    private void updateCache(UserInfoDto userInfoDto, Integer loginType) {
        //格式为：login_1_4
        valueOperations.set(SystemConstant.CACHE_NAME + loginType + "_" + userInfoDto.getId(), userInfoDto, Duration.ofHours(8));
    }

    /**
     * 用户退出
     * @param
     * @return void
     * @author zengxueqi
     * @since 2020/5/27
     */
    @Override
    public void logout() {
        UserInfoDto userInfoDto = UserUtils.getUser();
        if (userInfoDto != null) {
            redisTemplate.delete(SystemConstant.CACHE_NAME + userInfoDto.getLoginType() + "_" + tokenHandle.decodeAuth(userInfoDto.getToken()));
            redisTemplate.delete(SystemConstant.CACHE_NAME + userInfoDto.getLoginType() + "_" + userInfoDto.getId());
        }
    }

    /**
     * 用户注册
     * @param userRegisterDto
     * @return void
     * @author zengxueqi
     * @since 2020/5/16
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void userRegister(UserRegisterDto userRegisterDto) {
        //保存企业信息
        Company company = new Company();
        company.setCompanyName(userRegisterDto.getCompanyName());
        company.setCreditCode(userRegisterDto.getCreditCode());
        companyService.save(company);

        //保存企业用户信息
        String passwordSalt = Md5Utils.getRandomString(32);
        UserInfo userInfo = UserInfo.builder()
                .mobile(userRegisterDto.getMobile())
                .password(Md5Utils.generatePassword(userRegisterDto.getPassword(), passwordSalt))
                .pwdSalt(passwordSalt)
                .username(userRegisterDto.getUserName())
                .companyId(company.getId())
                .build();
        this.save(userInfo);
    }

    /**
     * 发送验证码
     * @param smsCodeDto
     * @return void
     * @author zengxueqi
     * @since 2020/5/18
     */
    @Override
    public void sendSmsCode(SmsCodeDto smsCodeDto) {
        if (SystemConstant.SYS_FALSE.equals(smsCodeDto.getUsageType())) {
            UserInfo userInfo = getUserInfoByMobile(smsCodeDto.getMobile());
            BnException.of(userInfo != null, "当前手机号已经注册过，请直接登录！");
        }
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);

        //生成短信验证码
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", regionId);
        request.putQueryParameter("PhoneNumbers", smsCodeDto.getMobile());
        request.putQueryParameter("SignName", siginName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + verifyCode + "\"}");

        try {
            CommonResponse response = client.getCommonResponse(request);
            if (response.getHttpStatus() == SystemConstant.HTTP_STATUS) {
                valueOperations.set(SmsCodeEnum.getDescByValue(smsCodeDto.getUsageType()) + smsCodeDto.getMobile(), verifyCode, SystemConstant.SMS_EXPIRE_LOGIN, TimeUnit.MINUTES);
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断验证码是否有效
     * @param smsCodeVerifyDto
     * @return com.july.company.vo.sms.SmsCodeVo
     * @author zengxueqi
     * @since 2020/5/18
     */
    @Override
    public SmsCodeVo verifySmsCode(SmsCodeVerifyDto smsCodeVerifyDto) {
        Object object = valueOperations.get(SmsCodeEnum.getDescByValue(smsCodeVerifyDto.getUsageType()) + smsCodeVerifyDto.getMobile());
        SmsCodeVo smsCodeVo = new SmsCodeVo();
        smsCodeVo.setSmsCodeValid(SystemConstant.SYS_FALSE);
        if (object != null) {
            smsCodeVo.setSmsCodeValid(SystemConstant.SYS_TRUE);
        }
        return smsCodeVo;
    }

    /**
     * 忘记密码时查询用户是否存在
     * @param userInfoValidDto
     * @return void
     * @author zengxueqi
     * @since 2020/5/19
     */
    @Override
    public UserInfoValidVo getUserInfoForForgetPassword(UserInfoValidDto userInfoValidDto) {
        BnException.of(StringUtils.isEmpty(userInfoValidDto.getMobile()), "找回密码时，手机号不能为空！");

        UserInfo userInfo = getUserInfoByMobile(userInfoValidDto.getMobile());

        UserInfoValidVo userInfoValidVo = new UserInfoValidVo();
        userInfoValidVo.setUserValid(userInfo != null ? SystemConstant.SYS_TRUE : SystemConstant.SYS_FALSE);
        return userInfoValidVo;
    }

    /**
     * 忘记密码
     * @param forgetPasswordDto
     * @return void
     * @author zengxueqi
     * @since 2020/5/19
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void forgetPassword(ForgetPasswordDto forgetPasswordDto) {
        BnException.of(StringUtils.isEmpty(forgetPasswordDto.getMobile()), "找回密码时，手机号不能为空！");
        UserInfo userInfo = getUserInfoByMobile(forgetPasswordDto.getMobile());
        BnException.of(userInfo == null, "当前手机号没有注册用户信息！");

        //新的密码盐值
        String passwordSalt = Md5Utils.getRandomString(32);
        userInfo.setPwdSalt(passwordSalt);
        userInfo.setPassword(Md5Utils.generatePassword(forgetPasswordDto.getPassword(), passwordSalt));
        this.updateById(userInfo);
    }

    /**
     * 获取用户信息
     * @param userId
     * @return com.july.company.dto.UserInfoDto
     * @author zengxueqi
     * @since 2020/5/16
     */
    @Override
    public UserInfoDto getUserInfo(Long userId) {
        UserInfo userInfo = this.getById(userId);
        UserInfoDto userInfoDto = new UserInfoDto();
        if (userInfo != null) {
            userInfoDto.setId(userInfo.getId());
            userInfoDto.setAvatar(userInfo.getAvatar());
            userInfoDto.setMobile(userInfo.getMobile());
            userInfoDto.setUserName(userInfo.getUsername());
        }
        return userInfoDto;
    }

    /**
     * 通过手机号获取用户信息
     * @param mobile
     * @return com.july.company.entity.UserInfo
     * @author zengxueqi
     * @since 2020/5/16
     */
    public UserInfo getUserInfoByMobile(String mobile) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile)
                //未禁用
                //.eq("status", SystemConstant.SYS_FALSE)
                //未删除
                .eq("deleted", SystemConstant.SYS_FALSE);
        return this.getOne(queryWrapper);
    }

    /**
     * 通过手机号和密码获取用户信息
     * @param mobile
     * @param password
     * @return com.july.company.entity.UserInfo
     * @author zengxueqi
     * @since 2020/5/16
     */
    public UserInfo getUserInfoByMobileAndPassword(String mobile, String password) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile)
                .eq("password", password)
                .eq("status", SystemConstant.SYS_FALSE)
                .eq("deleted", SystemConstant.SYS_FALSE);
        return this.getOne(queryWrapper);
    }

    /**
     * 禁用用户信息
     * @param userDisableDto
     * @return void
     * @author zengxueqi
     * @since 2020/5/27
     */
    @Override
    public void disableUser(UserDisableDto userDisableDto) {
        UserInfo userInfo = this.getById(userDisableDto.getUserId());
        BnException.of(userInfo == null, "没有找到该用户信息！");

        //禁用
        userInfo.setStatus(SystemConstant.SYS_TRUE);
        this.updateById(userInfo);

        //判断当前账号是否登录，如果登录则删除token缓存
        UserInfoDto userInfoDto = (UserInfoDto) valueOperations.get(SystemConstant.CACHE_NAME + userInfo.getId());
        if (userInfoDto != null) {
            String tokenUUID = tokenHandle.decodeAuth(userInfoDto.getToken());
            Object object = valueOperations.get(SystemConstant.CACHE_NAME + tokenUUID);
            if (object != null) {
                redisTemplate.delete(SystemConstant.CACHE_NAME + tokenUUID);
            }
        }
    }

    /**
     * 修改密码
     * @param changePasswordDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/5
     */
    @Override
    public void changePassword(ChangePasswordDto changePasswordDto) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", changePasswordDto.getMobile())
                .eq("status", SystemConstant.SYS_FALSE);
        UserInfo userInfo = this.getOne(queryWrapper);
        BnException.of(userInfo == null, "没有找到手机号为{}的用户！", changePasswordDto.getMobile());

        String passwordSalt = Md5Utils.generatePassword(changePasswordDto.getOldPassword(), userInfo.getPwdSalt());
        BnException.of(!userInfo.getPassword().equals(passwordSalt), "旧密码错误，请重新输入！");
        //加密后的密码相等
        userInfo.setPassword(Md5Utils.generatePassword(changePasswordDto.getNewPassword(), userInfo.getPwdSalt()));
        this.updateById(userInfo);
    }

    /**
     * 获取用户信息
     * @param selectUserDto
     * @return com.july.company.vo.user.SelectUserVo
     * @author zengxueqi
     * @since 2020/6/8
     */
    @Override
    public SelectUserVo getUserInfo(SelectUserDto selectUserDto) {
        BnException.of(selectUserDto.getUserId() == null, "用户id不能为空！");
        UserInfo userInfo = this.getById(selectUserDto.getUserId());
        if (userInfo != null) {
            Company company = companyService.getById(userInfo.getCompanyId());

            return SelectUserVo.builder()
                    .userId(userInfo.getId())
                    .companyName(company.getCompanyName())
                    .mobile(userInfo.getMobile())
                    .userName(userInfo.getUsername())
                    .sex(userInfo.getSex())
                    .build();
        }
        return SelectUserVo.builder().build();
    }

    /**
     * 更新用户信息
     * @param updateUserDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/8
     */
    @Override
    public void updateUserInfo(UpdateUserDto updateUserDto) {
        BnException.of(updateUserDto.getUserId() == null, "用户id不能为空！");
        UserInfo userInfo = this.getById(updateUserDto.getUserId());
        if (userInfo != null) {
            userInfo.setUsername(updateUserDto.getUserName());
            userInfo.setSex(updateUserDto.getSex());
            this.updateById(userInfo);
        }
    }

}
