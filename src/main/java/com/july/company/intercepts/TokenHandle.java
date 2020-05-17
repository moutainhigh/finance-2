package com.july.company.intercepts;

/**
 * token处理信息
 * @author zengxueqi
 * @program springboot-company
 * @since 2020-05-16 10:13
 **/
public interface TokenHandle {

    /**
     * 加密认证
     * @param rawStr 原始字符串
     * @return 加密后的内容
     */
    String encryptAuth(String rawStr, Long userId);

    /**
     * 解密认证
     * @param authStr 加密后传入的字符串
     * @return 解密后的内容
     */
    String decodeAuth(String authStr);

    /**
     * 解密获取userId
     * @param authStr 加密后传入的字符串
     * @return 解密后的内容
     */
    Long decodeUserId(String authStr);

}
