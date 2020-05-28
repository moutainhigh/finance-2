package com.july.company.monitor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.july.company.response.ResultT;
import com.july.company.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author ganlt<br>
 * @since 2019/07/02 8:57 <br>
 */
@Slf4j
@Component
public class MessageMonitor implements Monitor {
    @Resource
    private MonitorHandler monitorHandler;


    @Override
    public Object processMonitor(ProceedingJoinPoint pjp) throws Throwable {
        //sessionId处理
        String uuid = UUID.randomUUID().toString();
        String curSession = MDC.get(SESSION_ID);
        if (StringUtils.isEmpty(curSession)) {
            MDC.put(SESSION_ID, uuid);
        } else {
            uuid = curSession;
        }

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        MonitorRequest monitorRequest = new MonitorRequest();
        monitorRequest.setSessionId(uuid);
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            monitorRequest.setRequestIp(request.getHeader("remote_addr"));
            monitorRequest.setRequestUrl(request.getRequestURL().toString());
        }

        //处理方法签名
        String processMethodName = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();
        try {
            String argsJson = JSON.toJSONString(pjp.getArgs(), SerializerFeature.WriteMapNullValue);
            monitorRequest.setRequestArgs(argsJson);
        } catch (Exception e) {
            log.info("{}", "解析失败");
        }
        monitorRequest.setRequestSignatureMethod(processMethodName);

        try {
            if (log.isDebugEnabled()) {
                //log.debug("{}", monitorRequest.beforeFormatDump());
            }
            Object result = pjp.proceed(pjp.getArgs());
            try {
                if (result instanceof ResultT) {
                    String resultJson = JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
                    monitorRequest.setReturnResult(resultJson);
                } else {
                    monitorRequest.setReturnResult("其他返回类型，跳过");
                }
            } catch (Exception e) {
                log.info("{}", "解析失败");
            }
            if (log.isDebugEnabled()) {
                //log.debug("{}", monitorRequest.afterFormatDump());
            }
            // MDC.remove(SESSION_ID);
            return result;
        } catch (Throwable throwable) {
            monitorHandler.advice(throwable, monitorRequest);
            throw throwable;
        }

    }
}
