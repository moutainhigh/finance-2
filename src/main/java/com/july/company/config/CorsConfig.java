package com.july.company.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 设置跨域请求, 线上已有改功能,发布前,将其注释
 * 发布的时候值需要修改配置禁用该bean就行了
 * @author zengxueqi
 * @since 2020/05/21
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "cors", name = "enabled", havingValue = "true")
public class CorsConfig {

    /**
     * 注册跨域访问filter
     * @return 返回注册的bean
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        //设置你要允许的网站域名，如果全允许则设为 *
        config.addAllowedOrigin("*");
        //如果要限制 HEADER 或 METHOD 请自行更改
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        //这个顺序很重要哦，为避免麻烦请设置在最前
        bean.setOrder(0);
        log.info("配置跨域bean");
        return bean;
    }

}
