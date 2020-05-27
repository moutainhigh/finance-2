package com.july.company.intercepts;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.net.NetUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.july.company.constant.SystemConstant;
import com.july.company.dto.user.UserInfoDto;
import com.july.company.exception.BnException;
import com.july.company.service.UserInfoService;
import com.july.company.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 拦截用户登录信息
 * @author zengxueqi
 * @since 2020/05/20
 */
@Component
@Slf4j
public class LoginIntercept implements HandlerInterceptor {

    @Resource
    ValueOperations<String, UserInfoDto> valueOperations;
    @Resource
    TokenHandle tokenHandle;
    @Resource
    RedisTemplate<String, UserInfoDto> redisTemplate;
    @Resource
    UserInfoService userInfoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //通过header 获取token
        String remoteIp = envConvertInner(ServletUtil.getClientIP(request));
        final String authorization = "Authorization";

        String authToken = request.getHeader(authorization);
        if (NetUtil.isInnerIP(remoteIp) && StringUtils.isEmpty(authToken)) {
            //允许内网直接调用接口
            Map<String, String> paramMap = ServletUtil.getParamMap(request);
            Long userId = Convert.toLong(paramMap.getOrDefault("userId", ""));
            UserInfoDto userInfoDto = userInfoService.getUserInfo(userId);
            log.info("登录用户信息:{}", userInfoDto);
            if (userInfoDto != null) {
                UserUtils.setUser(userInfoDto);
            } else {
                UserInfoDto userJsonObj = new UserInfoDto();
                userJsonObj.setId(userId);
                UserUtils.setUser(userJsonObj);
            }
            log.info("ip:{},内网调用接口:调用接口地址:{},userId:{}", remoteIp, request.getRequestURI(), userId);
            return true;
        }
        if (StringUtils.isEmpty(authToken)) {
            throw new BnException(SystemConstant.LOGIN_EXCEPTION, "登录信息不存在");
        }
        String loginKey = tokenHandle.decodeAuth(authToken);

        Object userIdObj = valueOperations.get(SystemConstant.CACHE_NAME + loginKey);
        if (userIdObj == null) {
            throw new BnException(SystemConstant.LOGIN_EXCEPTION, "未登录");
        }
        String userId = String.valueOf(userIdObj);
        UserInfoDto userJsonObj = valueOperations.get(SystemConstant.CACHE_NAME + userId);
        if (userJsonObj == null) {
            throw new BnException(SystemConstant.LOGIN_EXCEPTION, "未登录");
        }
        updateExpire(loginKey);
        UserUtils.setUser(userJsonObj);
        return true;
    }

    /**
     * 环境转换
     * @param ip 实际ip地址
     * @return 测试服ip转为内网ip
     */
    private String envConvertInner(String ip) {
        if ("172.16.181.205".equalsIgnoreCase(ip)) {
            return "127.0.0.1";
        }
        return ip;
    }

    /**
     * 刷新登录时间
     * @param loginKey 登录token
     * @author ganlt
     * @date 2019/12/4 11:02
     */
    private void updateExpire(String loginKey) {
        redisTemplate.expire(SystemConstant.CACHE_NAME + loginKey, SystemConstant.EXPIRE_LOGIN, TimeUnit.MINUTES);
    }

}
