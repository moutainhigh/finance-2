package com.july.company.service;

import com.july.company.dto.UserInfoDto;
import com.july.company.dto.login.LoginAuthDto;
import com.july.company.dto.login.UserRegisterDto;
import com.july.company.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * code加入缓存
     * @param flag
     * @param key
     * @param code
     * @return void
     * @author zengxueqi
     * @since 2020/5/16
     */
    void codeCache(String flag, String key, String code);

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

}
