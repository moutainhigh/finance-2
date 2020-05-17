package com.july;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.TimeZone;

@SpringBootApplication
@MapperScan("com.july.company.mapper")
@EnableCaching
public class CompanyApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(CompanyApplication.class, args);
    }

}
