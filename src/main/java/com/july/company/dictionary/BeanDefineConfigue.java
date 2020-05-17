package com.july.company.dictionary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 系统初始化字典数据
 * @author zengxueqi
 * @since 2020/05/17
 */
@Slf4j
@Component("BeanDefineConfigue")
public class BeanDefineConfigue implements ApplicationListener<ApplicationReadyEvent> {

    @Resource
    private DictInit dictInit;

    /**
     * 当一个ApplicationContext被初始化或刷新触发
     * @param event
     * @return void
     * @author zengxueqi
     * @since 2020/5/17
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        if(event.getApplicationContext()!=null){
            log.info("字典：START:spring初始化开始======================>");
            dictInit.start();
            log.info("字典:END:spring初始化完毕======================>");
        }
    }
}
