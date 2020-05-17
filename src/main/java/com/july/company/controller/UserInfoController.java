package com.july.company.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.july.company.constant.SystemConstant;
import com.july.company.dto.UserImgCodeDto;
import com.july.company.dto.UserInfoDto;
import com.july.company.dto.login.LoginAuthDto;
import com.july.company.dto.login.UserRegisterDto;
import com.july.company.response.ResultT;
import com.july.company.service.UserInfoService;
import com.july.company.vo.login.LoginAuthVo;
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
     * 生成图片验证码
     * @param UserImgCodeDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/5/16
     */
    @PostMapping("/generatorImgCode")
    public ResultT<String> generatorImgCode(@Valid @RequestBody UserImgCodeDto UserImgCodeDto) {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100);
        String imageBase64 = captcha.getImageBase64();
        String code = captcha.getCode();
        userInfoService.codeCache(SystemConstant.IMG_CODE_FLAG, UserImgCodeDto.getCode(), code);
        return ResultT.ok(imageBase64);
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

}
