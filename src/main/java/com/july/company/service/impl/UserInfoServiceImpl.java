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
import com.july.company.dto.UserInfoDto;
import com.july.company.dto.login.LoginAuthDto;
import com.july.company.dto.login.UserRegisterDto;
import com.july.company.dto.sms.SmsCodeDto;
import com.july.company.dto.sms.SmsCodeVerifyDto;
import com.july.company.entity.UserInfo;
import com.july.company.exception.BnException;
import com.july.company.intercepts.TokenHandle;
import com.july.company.mapper.UserInfoMapper;
import com.july.company.response.ResultT;
import com.july.company.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.utils.Md5Utils;
import com.july.company.utils.UUIDUtils;
import com.july.company.utils.VerifyCodeUtils;
import com.july.company.vo.sms.SmsCodeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.Duration;
import java.util.Base64;
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
    @Resource(name = "redisTemplate")
    ValueOperations<String, Object> valueOperations;
    @Resource
    private TokenHandle tokenHandle;

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

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .userName(checkUserInfo.getUsername())
                .avatar(checkUserInfo.getAvatar())
                .mobile(checkUserInfo.getMobile())
                .sex(checkUserInfo.getSex())
                .id(checkUserInfo.getId())
                .token(tokenDes)
                .build();
        valueOperations.set(SystemConstant.CACHE_NAME + token, userInfoDto.getId(), SystemConstant.EXPIRE_LOGIN, TimeUnit.MINUTES);
        return userInfoDto;
    }

    /**
     * code加入缓存
     * @param flag
     * @param key
     * @param code
     * @return void
     * @author zengxueqi
     * @since 2020/5/16
     */
    @Override
    public void codeCache(String flag, String key, String code) {
        Object o = valueOperations.get(flag + key);
        if (o != null) {
            throw BnException.on("请不要重复生成验证码！");
        }
        valueOperations.set(flag + key, code, Duration.ofMinutes(15));
    }

    /**
     * 用户注册
     * @param userRegisterDto
     * @return void
     * @author zengxueqi
     * @since 2020/5/16
     */
    @Override
    public void userRegister(UserRegisterDto userRegisterDto) {
        UserInfo userInfoExist = getUserInfoByMobile(userRegisterDto.getMobile());
        BnException.of(userInfoExist != null, "当前手机号以前存在，请直接登录！");

        String passwordSalt = Md5Utils.getRandomString(32);
        UserInfo userInfo = UserInfo.builder()
                .mobile(userRegisterDto.getMobile())
                .password(Md5Utils.generatePassword(userRegisterDto.getPassword(), passwordSalt))
                .pwdSalt(passwordSalt)
                .username(userRegisterDto.getUserName())
                .companyName(userRegisterDto.getCompanyName())
                .creditCode(userRegisterDto.getCreditCode())
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
                valueOperations.set(SystemConstant.SMS_REGISTER + smsCodeDto.getMobile(), verifyCode, SystemConstant.SMS_EXPIRE_LOGIN, TimeUnit.MINUTES);
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
        Object object = valueOperations.get(SystemConstant.SMS_REGISTER + smsCodeVerifyDto.getMobile());
        SmsCodeVo smsCodeVo = new SmsCodeVo();
        smsCodeVo.setSmsCodeValid(SystemConstant.SYS_FALSE);
        if (object != null) {
            smsCodeVo.setSmsCodeValid(SystemConstant.SYS_TRUE);
        }
        return smsCodeVo;
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

    /**
     * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
     * @param response
     * @return ResultT<String>
     * @author zengxueqi
     * @since 2020/5/18
     */
    @PostMapping("/genCaptcha")
    public ResultT<String> genCaptcha(HttpServletResponse response) {
        try {
            //设置页面不缓存
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            String verifyCode = VerifyCodeUtils.generateTextCode(VerifyCodeUtils.TYPE_ALL_MIXED, 4, null);
            //将验证码放到HttpSession里面

            response.setHeader("Access-Control-Expose-Headers:validateCode", verifyCode);
            response.setHeader("validateCodetest", verifyCode);
            //设置输出的内容的类型为JPEG图像
            // response.setContentType("image/jpeg");
            BufferedImage bufferedImage = VerifyCodeUtils.generateImageCode(verifyCode, 117, 40, 5, true, new Color(249, 205, 173), null, null);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
            ImageIO.write(bufferedImage, "JPEG", baos);//写入流中
            byte[] bytes = baos.toByteArray();//转换成字节
            Base64.Encoder encoder = Base64.getEncoder();
            String png_base64 = encoder.encodeToString(bytes).trim();//转换成base64串
            png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
            return ResultT.ok(verifyCode + ":" + png_base64);
        } catch (Exception e) {
            e.printStackTrace();
            ResultT.error(e.getMessage());
        }
        return ResultT.error("验证码获取失败");
    }

}
