package com.july.company.intercepts;

import com.july.company.constant.SystemConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 拦截器配置信息
 * @author zengxueqi
 * @date 2019/11/26
 */
@Configuration
public class InterceptConfig implements WebMvcConfigurer {

    @Resource
    private LoginIntercept loginIntercept;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntercept).excludePathPatterns(SystemConstant.LOGIN_VERIFY_EXCLUDE_URL).excludePathPatterns("/*.txt");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/template/");
    }

}
