package com.july.company.utils;

import com.july.company.dto.user.UserInfoDto;

/**
 * 获取用户信息
 * @author zengxueqi
 * @program springboot-company
 * @since 2020-05-16 15:33
 **/
public class UserUtils {

    private static ThreadLocal<UserInfoDto> userThreadLocal = new ThreadLocal<>();

    public static void setUser(UserInfoDto user) {
        userThreadLocal.set(user);
    }

    public static UserInfoDto getUser() {
        return userThreadLocal.get();
    }

}
