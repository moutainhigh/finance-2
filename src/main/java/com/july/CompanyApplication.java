package com.july;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

import java.util.TimeZone;

@SpringBootApplication
//@MapperScan("com.july.company.mapper") 在intercepts里面配置，所以此处可以省略
//@EnableFeignClients(basePackages = {"com.company.api.**.api"}) 需要调用外部服务时才需要添加该注解
//@EnableConfigurationProperties({ClientProperties.class}) 需要调用外部服务时才需要添加该注解
@EnableCaching
public class CompanyApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CompanyApplication.class);
    }

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(CompanyApplication.class, args);
    }
}
