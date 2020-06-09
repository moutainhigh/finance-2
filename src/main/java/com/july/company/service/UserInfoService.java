package com.july.company.service;

import com.july.company.dto.login.*;
import com.july.company.dto.sms.SmsCodeDto;
import com.july.company.dto.sms.SmsCodeVerifyDto;
import com.july.company.dto.user.*;
import com.july.company.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.july.company.vo.login.UserInfoValidVo;
import com.july.company.vo.sms.SmsCodeVo;
import com.july.company.vo.user.SelectUserVo;

/**
 * 用户服务
 * @author zengxueqi
 * @since 2020-05-16
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 用户登录
     * @param loginAuthDto
     * @return com.july.company.dto.UserInfoDto
     * @author zengxueqi
     * @since 2020/5/16
     */
    UserInfoDto login(LoginAuthDto loginAuthDto);

    /**
     * 用户注册
     * @param userRegisterDto
     * @return void
     * @author zengxueqi
     * @since 2020/5/16
     */
    void userRegister(UserRegisterDto userRegisterDto);

    /**
     * 获取用户信息
     * @param userId
     * @return com.july.company.dto.UserInfoDto
     * @author zengxueqi
     * @since 2020/5/16
     */
    UserInfoDto getUserInfo(Long userId);

    /**
     * 发送验证码
     * @param smsCodeDto
     * @return void
     * @author zengxueqi
     * @since 2020/5/18
     */
    void sendSmsCode(SmsCodeDto smsCodeDto);

    /**
     * 判断验证码是否有效
     * @param smsCodeVerifyDto
     * @return com.july.company.vo.sms.SmsCodeVo
     * @author zengxueqi
     * @since 2020/5/18
     */
    SmsCodeVo verifySmsCode(SmsCodeVerifyDto smsCodeVerifyDto);

    /**
     * 忘记密码时查询用户是否存在
     * @param userInfoValidDto
     * @return void
     * @author zengxueqi
     * @since 2020/5/19
     */
    UserInfoValidVo getUserInfoForForgetPassword(UserInfoValidDto userInfoValidDto);

    /**
     * 忘记密码
     * @param forgetPasswordDto
     * @return void
     * @author zengxueqi
     * @since 2020/5/19
     */
    void forgetPassword(ForgetPasswordDto forgetPasswordDto);

    /**
     * 用户退出
     * @param
     * @return void
     * @author zengxueqi
     * @since 2020/5/27
     */
    void logout();

    /**
     * 禁用用户信息
     * @param userDisableDto
     * @return void
     * @author zengxueqi
     * @since 2020/5/27
     */
    void disableUser(UserDisableDto userDisableDto);

    /**
     * 修改密码
     * @param changePasswordDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/5
     */
    void changePassword(ChangePasswordDto changePasswordDto);

    /**
     * 获取用户信息
     * @param selectUserDto
     * @return com.july.company.vo.user.SelectUserVo
     * @author zengxueqi
     * @since 2020/6/8
     */
    SelectUserVo getUserInfo(SelectUserDto selectUserDto);

    /**
     * 更新用户信息
     * @param updateUserDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/8
     */
    void updateUserInfo(UpdateUserDto updateUserDto);

}
