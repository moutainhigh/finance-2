package com.july.company.monitor;

/**
 * 监控异常通知
 * @author ganlt<br>
 * @since 2019/07/02 9:12 <br>
 */
public interface MonitorHandler {

    /**
     * 异常通知处理
     * @param monitorRequest 监控参数
     * @param throwable 异常对象
     */
    void advice(Throwable throwable, MonitorRequest monitorRequest);

}
