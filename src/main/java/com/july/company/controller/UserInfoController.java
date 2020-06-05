package com.july.company.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.july.company.constant.SystemConstant;
import com.july.company.dto.login.*;
import com.july.company.dto.sms.SmsCodeDto;
import com.july.company.dto.sms.SmsCodeVerifyDto;
import com.july.company.dto.user.ChangePasswordDto;
import com.july.company.dto.user.UserDisableDto;
import com.july.company.dto.user.UserInfoDto;
import com.july.company.entity.UserInfo;
import com.july.company.response.ResultT;
import com.july.company.service.UserInfoService;
import com.july.company.vo.login.LoginAuthVo;
import com.july.company.vo.login.UserInfoValidVo;
import com.july.company.vo.sms.SmsCodeVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 前端控制器
 * @author zengxueqi
 * @since 2020-05-16
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    /**
     * 用户登录
     * @param loginAuthDto
     * @return com.july.company.response.ResultT<com.july.company.vo.login.LoginAuthVo>
     * @author zengxueqi
     * @since 2020/5/16
     */
    @PostMapping("/login")
    public ResultT<LoginAuthVo> login(@Valid @RequestBody LoginAuthDto loginAuthDto) {
        //验证参数值
        UserInfoDto userInfoDto = userInfoService.login(loginAuthDto);
        LoginAuthVo loginAuthVo = LoginAuthVo.builder()
                .token(userInfoDto.getToken())
                .userInfo(userInfoDto)
                .build();
        return ResultT.ok(loginAuthVo);
    }

    /**
     * 用户注册
     * @param userRegisterDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/5/16
     */
    @PostMapping("/register")
    public ResultT<String> register(@RequestBody UserRegisterDto userRegisterDto) {
        userInfoService.userRegister(userRegisterDto);
        return ResultT.ok("注册成功！");
    }

    /**
     * 发送验证码
     * @param smsCodeDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/5/18
     */
    @PostMapping("/sendSmsCode")
    public ResultT<String> sendSmsCode(@Valid @RequestBody SmsCodeDto smsCodeDto) {
        userInfoService.sendSmsCode(smsCodeDto);
        return ResultT.ok("验证码发送成功！");
    }

    /**
     * 验证验证码是否有效
     * @param smsCodeVerifyDto
     * @return com.july.company.response.ResultT<com.july.company.vo.sms.SmsCodeVo>
     * @author zengxueqi
     * @since 2020/5/18
     */
    @PostMapping("/verifySmsCode")
    public ResultT<SmsCodeVo> verifySmsCode(@Valid @RequestBody SmsCodeVerifyDto smsCodeVerifyDto) {
        return ResultT.ok(userInfoService.verifySmsCode(smsCodeVerifyDto));
    }

    /**
     * 忘记密码时查询用户是否存在
     * @param userInfoValidDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/5/19
     */
    @PostMapping("/getUserInfoForForgetPassword")
    public ResultT<UserInfoValidVo> getUserInfoForForgetPassword(@RequestBody UserInfoValidDto userInfoValidDto) {
        return ResultT.ok(userInfoService.getUserInfoForForgetPassword(userInfoValidDto));
    }

    /**
     * 忘记密码
     * @param forgetPasswordDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/5/19
     */
    @PostMapping("/forgetPassword")
    public ResultT<String> forgetPassword(@RequestBody ForgetPasswordDto forgetPasswordDto) {
        userInfoService.forgetPassword(forgetPasswordDto);
        return ResultT.ok("密码修改成功！");
    }

    /**
     * 用户退出
     * @param
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/5/27
     */
    @PostMapping("/logout")
    public ResultT<String> logout() {
        userInfoService.logout();
        return ResultT.ok("退出成功！");
    }

    /**
     * 用户禁用
     * @param userDisableDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/5/27
     */
    @PostMapping("/disableUser")
    public ResultT<String> disableUser(@RequestBody UserDisableDto userDisableDto) {
        userInfoService.disableUser(userDisableDto);
        return ResultT.ok("用户禁用成功！");
    }

    public ResultT<String> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        return ResultT.ok("密码修改成功！");
    }

}
