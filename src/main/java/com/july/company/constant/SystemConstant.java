package com.july.company.constant;

/**
 * 系统常量
 * @author zengxueqi
 * @program springboot-company
 * @since 2020-05-16 09:55
 **/
public class SystemConstant {

    /**
     * 是（正确）
     */
    public static final Integer SYS_TRUE = 0;
    /**
     * 否（错误）
     */
    public static final Integer SYS_FALSE = 1;
    /**
     * 登录缓存前缀名称
     */
    public static final String CACHE_NAME = "login_";
    /**
     * 登录过期时间(分)
     */
    public static final Integer EXPIRE_LOGIN = 8 * 60;
    /**
     * 图片code前缀
     */
    public static final String IMG_CODE_FLAG = "imgCodeFlag_";
    /**
     * 登录异常状态码
     */
    public static final Integer LOGIN_EXCEPTION = -1000;
    /**
     * 排除指定url，不进行登录验证
     */
    public static String[] LOGIN_VERIFY_EXCLUDE_URL = {"/userInfo/login", "/userInfo/forgetPassword",
            "/userInfo/sendForgetPasswordCode", "/userInfo/generatorImgCode", "/authorize", "/userInfo/getAuthRedirect", "/enum/**"};

}
