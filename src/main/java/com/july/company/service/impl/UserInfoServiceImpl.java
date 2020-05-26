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
import com.july.company.dto.login.ForgetPasswordDto;
import com.july.company.dto.login.LoginAuthDto;
import com.july.company.dto.login.UserInfoValidDto;
import com.july.company.dto.login.UserRegisterDto;
import com.july.company.dto.sms.SmsCodeDto;
import com.july.company.dto.sms.SmsCodeVerifyDto;
import com.july.company.dto.user.UserInfoDto;
import com.july.company.entity.Company;
import com.july.company.entity.UserInfo;
import com.july.company.entity.enums.SmsCodeEnum;
import com.july.company.exception.BnException;
import com.july.company.intercepts.TokenHandle;
import com.july.company.mapper.UserInfoMapper;
import com.july.company.service.CompanyService;
import com.july.company.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.utils.Md5Utils;
import com.july.company.utils.UUIDUtils;
import com.july.company.vo.login.UserInfoValidVo;
import com.july.company.vo.sms.SmsCodeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
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
    @Resource
    ValueOperations<String, Object> valueOperations;
    @Resource
    private TokenHandle tokenHandle;
    @Resource
    private CompanyService companyService;

    @Override
    public UserInfoDto login(LoginAuthDto loginAuthDto) {
        String mobile = loginAuthDto.getMobile();
        log.trace("用户手机:{},登录系统", loginAuthDto.getMobile());
        //数据库匹配用户
        UserInfo userInfo = this.getUserInfoByMobile(loginAuthDto.getMobile());
        if (userInfo == null) {
            throw BnException.on("用户不存在");
        }
        UserInfo checkUserInfo = this.getUserInfoByMobileAndPassword(mobile, loginAuthDto.getEncryptPassword(userInfo.getPwdSalt()));
        if (checkUserInfo == null) {
            throw BnException.on("用户名或密码错误");
        }
        if (checkUserInfo.getDeleted() == 1) {
            throw BnException.on("账号已被删除");
        }
        String token = UUIDUtils.getReplaceUuid();
        String tokenDes = tokenHandle.encryptAuth(token, checkUserInfo.getId());

        Company company = companyService.getById(checkUserInfo.getCompanyId());

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .userName(checkUserInfo.getUsername())
                .avatar(checkUserInfo.getAvatar())
                .mobile(checkUserInfo.getMobile())
                .sex(checkUserInfo.getSex())
                .id(checkUserInfo.getId())
                .token(tokenDes)
                .companyName(company != null ? company.getCompanyName() : "")
                .build();
        valueOperations.set(SystemConstant.CACHE_NAME + token, userInfoDto.getId(), SystemConstant.EXPIRE_LOGIN, TimeUnit.MINUTES);
        return userInfoDto;
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
        UserInfo userInfoExist = getUserInfoByMobile(userRegisterDto.getMobile());
        BnException.of(userInfoExist != null, "当前手机号以前存在，请直接登录！");

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
        if(SystemConstant.SYS_FALSE.equals(smsCodeDto.getUsageType())){
            UserInfo userInfo = getUserInfoByMobile(smsCodeDto.getMobile());
            BnException.of(userInfo != null,"当前手机号已经注册过，请直接登录！");
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
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", smsCodeDto.getMobile());
        request.putQueryParameter("SignName", "成都大数据产业技术研究院");
        request.putQueryParameter("TemplateCode", "SMS_135033715");
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
            userInfoDto.setSex(userInfo.getSex());
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
                .eq("deleted", SystemConstant.SYS_FALSE);
        return this.getOne(queryWrapper);
    }

}
