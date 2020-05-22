package com.july.company.intercepts;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis 公共配置
 * @author zengxueqi
 * @since 2020/05/22
 */
@Slf4j
@Configuration
@MapperScan(basePackages = {"com.july.**.mapper"})
public class MybatisPlusConfig {

    /**
     * 分页插件
     * @param
     * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     * @author zengxueqi
     * @since 2020/5/22
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        log.info("mybatis分页拦截器初始化.");
        return new PaginationInterceptor();
    }

    /**
     * 用于自动对公共字段的填充，包含updatedTime,createdTime,deleted字段
     * @param
     * @return com.baomidou.mybatisplus.core.handlers.MetaObjectHandler 公共字段处理器
     * @author zengxueqi
     * @since 2020/5/22
     */
    @Bean
    public MetaObjectHandler myMetaObjectHandler() {
        log.info("mybatis公共字段填充处理器初始化.");
        return new MyMetaObjectHandler();
    }

}
