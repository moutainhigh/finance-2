package com.july.company.intercepts;

import com.july.company.monitor.Monitor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zengxueqi
 * @since 2019/07/02
 */
@Aspect
@Component
public class MonitorIntercept {

    @Resource
    private Monitor monitor;

    @Pointcut("execution(* com.july.company.service.impl..*.*(..)) || execution(* com.july.company.controller..*.*(..))")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        return monitor.processMonitor(pjp);
    }

}
