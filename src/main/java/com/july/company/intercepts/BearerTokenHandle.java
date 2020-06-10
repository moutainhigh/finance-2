package com.july.company.intercepts;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import com.july.company.exception.BnException;
import com.july.company.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * token处理信息
 * @author zengxueqi
 * @program springboot-company
 * @since 2020-05-16 10:16
 **/
@Component
public class BearerTokenHandle implements TokenHandle {

    private static final String TOKEN_STR = "token";
    private static final String USER_ID_STR = "userId";
    private static final String LOGIN_TYPE = "loginType";
    private static final String SIGNAL_HEAD = "Bearer ";
    @Value("${jwt.accessKeyId}")
    private String appName = "appName";

    @Override
    public String encryptAuth(String rawStr, Integer loginType, Long userId) {
        Map<String, Object> map = MapUtil.newHashMap();
        map.put(TOKEN_STR, rawStr);
        map.put(USER_ID_STR, userId);
        map.put(LOGIN_TYPE, loginType);
        return JwtUtils.signature(map, "", "", appName);
    }

    @Override
    public String decodeAuth(String authStr) {
        String jwtStr = authStr.replace(SIGNAL_HEAD, "").trim();
        Claims claims = JwtUtils.decodeJwt(jwtStr, appName);
        if (claims == null) {
            throw new BnException(-1000, "加密解析失败");
        }
        Object tokenObj = claims.get(TOKEN_STR);
        if (tokenObj == null) {
            throw new BnException(-1000, "加密解析失败");
        }
        return String.valueOf(tokenObj);
    }

    @Override
    public Long decodeUserId(String authStr) {
        String jwtStr = authStr.replace(SIGNAL_HEAD, "").trim();
        Claims claims = JwtUtils.decodeJwt(jwtStr, appName);
        if (claims == null) {
            throw new BnException(-1000, "加密解析失败");
        }
        return Convert.toLong(claims.get(USER_ID_STR));
    }

    @Override
    public Integer decodeLoginType(String authStr) {
        String jwtStr = authStr.replace(SIGNAL_HEAD, "").trim();
        Claims claims = JwtUtils.decodeJwt(jwtStr, appName);
        if (claims == null) {
            throw new BnException(-1000, "加密解析失败");
        }
        return Convert.toInt(claims.get(LOGIN_TYPE));
    }

}
