package com.july.company.utils;

import java.util.UUID;

/**
 * uuid生成工具
 * @author zengxueqi
 * @program springboot-company
 * @since 2020-05-16 10:05
 **/
public class UUIDUtils {

    /**
     * 获取UUid前13位
     * @param
     * @return java.lang.String
     * @author zengxueqi
     * @since 2020/5/16
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().toUpperCase().substring(0, 12);
    }

    /**
     * 获取UUid前13位，删除其中的"-"符号，并转化为大写
     * @param
     * @return java.lang.String
     * @author zengxueqi
     * @since 2020/5/16
     */
    public static String getReplaceUuid() {
        return getSaltUuid().toUpperCase();
    }

    /**
     * 获取UUid前13位，删除其中的"-"符号
     * @param
     * @return java.lang.String
     * @author zengxueqi
     * @since 2020/5/16
     */
    public static String getSaltUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
