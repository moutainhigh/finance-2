package com.july.company.utils;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * jwt工具类
 * @author zengxueqi
 * @program springboot-company
 * @since 2020-05-16 10:33
 **/
@Slf4j
public class JwtUtils {

    /**
     * 指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
     */
    private static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private static final int MILLISESCOND_TO_SECOND = 1000;
    private static final int EXP_SENCOND = 300;

    /**
     * 签名
     * @param request     请求
     * @param fromService 本服务
     * @param toService   调用服务
     * @param password    密码
     * @return 签名后字符串
     */
    public static String signature(Object request, String fromService, String toService, String password) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("from_service", fromService); // 原始服务
        claims.put(Claims.ISSUER, toService); // 调用的服务
        claims.put(Claims.ISSUED_AT, System.currentTimeMillis() / MILLISESCOND_TO_SECOND); // 时间戳(秒)
        // 时间戳(秒), 由于历史原因,nbf参数被当作exp来使用了。
        claims.put(Claims.NOT_BEFORE, System.currentTimeMillis() / MILLISESCOND_TO_SECOND + EXP_SENCOND);

        StringBuffer sb = new StringBuffer();
        String payload1 = request.toString();
        if (!JSON.isValidObject(payload1)) {
            payload1 = JSON.toJSONString(request);
        }
        sb.append(payload1.substring(0, payload1.lastIndexOf("}")));
        sb.append(",");

        String payload2 = JSON.toJSONString(claims);
        sb.append(payload2.substring(payload2.indexOf("{") + 1));
        log.info("{}", sb.toString());

        JwtBuilder builder = Jwts.builder().setPayload(sb.toString()).signWith(signatureAlgorithm, password.getBytes());
        return builder.compact();
    }

    /**
     * 签名
     * @param param       签名对象
     * @param fromService 本服务
     * @param toService   调用服务
     * @param password    密码
     * @param delay       延迟时间(时间单位秒)
     * @return 签名后字符串
     */
    public static String signature(Map<String, Object> param, String fromService, String toService, String password, int delay) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("from_service", fromService); // 原始服务
        claims.put(Claims.ISSUER, toService); // 调用的服务
        claims.put(Claims.ISSUED_AT, System.currentTimeMillis() / MILLISESCOND_TO_SECOND); // 时间戳(秒)
        claims.put(Claims.NOT_BEFORE, System.currentTimeMillis() / MILLISESCOND_TO_SECOND + delay); // 时间戳(秒)
        claims.putAll(param);
        JwtBuilder builder = Jwts.builder().setClaims(claims).signWith(signatureAlgorithm, password.getBytes());
        log.info("{}", claims);
        return builder.compact();
    }

    /**
     * 无延迟签名
     * @param param       签名对象
     * @param fromService 本服务
     * @param toService   调用服务
     * @param password    密码
     * @return 签名结果
     */
    public static String signature(Map<String, Object> param, String fromService, String toService, String password) {
        return signature(param, fromService, toService, password, 0);
    }

    /**
     * 解析jwt数据
     * @param token 加密后的token
     * @return Claims
     */
    public static Claims decodeJwt(String token, String key) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key.getBytes())
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            claims = null;
        }
        return claims;
    }

}
