package com.july.company.utils;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.util.Random;

/**
 * md5工具类
 * @author zengxueqi
 * @program springboot-company
 * @since 2020-05-16 09:35
 **/
public class Md5Utils {

    /**
     * 生成含有随机盐的密码
     * @param password
     * @param salt
     * @return java.lang.String
     * @author zengxueqi
     * @since 2020/5/16
     */
    public static String generatePassword(String password, String salt) {
        return md5Hex(salt + password);
    }

    /**
     * 获取十六进制字符串形式的MD5摘要
     * @param src
     * @return java.lang.String
     * @author zengxueqi
     * @since 2020/5/16
     */
    public static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获取盐值（随机字符串）
     * @param length
     * @return java.lang.String
     * @author zengxueqi
     * @since 2020/5/16
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
