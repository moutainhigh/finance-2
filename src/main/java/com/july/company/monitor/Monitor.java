package com.july.company.monitor;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 监控处理
 * @author ganlt<br>
 * @since 2019/07/02 8:43 <br>
 */
public interface Monitor {

    String SESSION_ID = "sessionId";

    /**
     * 拦截监听方法处理
     * @param pjp 拦截对象
     * @return 执行后结果
     * @throws Throwable 异常对象
     */
    Object processMonitor(ProceedingJoinPoint pjp) throws Throwable;

}
