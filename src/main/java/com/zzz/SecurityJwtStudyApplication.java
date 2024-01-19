package com.zzz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zzz.mapper")
public class SecurityJwtStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityJwtStudyApplication.class, args);
    }

}
