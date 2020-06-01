package com.july;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.TimeZone;

@SpringBootApplication
//@MapperScan("com.july.company.mapper") 在intercepts里面配置，所以此处可以省略
@EnableCaching
public class CompanyApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(CompanyApplication.class, args);
    }

}
