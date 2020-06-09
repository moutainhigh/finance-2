package com.july.company.monitor;

import cn.snowheart.dingtalk.robot.starter.client.DingTalkRobotClient;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.july.company.exception.BnException;
import com.july.company.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author zengxueqi
 * @since 2019/07/02
 */
@Component
@RefreshScope
@Slf4j
public class DingMonitorHandler implements MonitorHandler {

    @Resource(name = "dingTalkRobotClient")
    private DingTalkRobotClient dingTalkRobotClient;
    @Value("${dingtalk.robot.enabled}")
    private boolean enabled;
    @Value("${spring.application.name}")
    private String appName;

    private static Cache<String, String> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(1, TimeUnit.SECONDS)
            .build();

    @Override
    @Async
    public void advice(Throwable throwable, MonitorRequest monitorRequest) {
        //非业务异常处理
        if (!(throwable instanceof BnException)) {
            String printStackStr = Arrays.toString(throwable.getStackTrace());
            String signal = StringUtils.md5(printStackStr);
            String cacheSignal = cache.getIfPresent(signal);
            if (!StringUtils.isEmpty(cacheSignal)) {
                log.debug("存在相同异常，退出");
                return;
            }
            cache.put(signal, "");

            String title = "环境:[" + SpringHelper.getProfile() + "] " + "项目:" + appName + "发生异常[" + throwable.toString() + "]";
            String message = assembleMessage(monitorRequest, title, printStackStr);
            
            if (enabled) {
                dingTalkRobotClient.sendActionCardMessage(title, message);
            }
        }
    }

    private String assembleMessage(MonitorRequest request, String title, String printStackStr) {
        String requestStr = request.beforeFormatDump();
        String responseStr = request.afterFormatDump();
        StringBuilder sb = new StringBuilder();
        sb.append(title).append("\n");
        sb.append(requestStr).append("==============").append("\n");
        sb.append(responseStr).append("==============").append("\n");
        sb.append("--------异常信息------ ").append("[").append("]").append("\n");
        sb.append(printStackStr).append("\n");

        return sb.toString();
    }

}
