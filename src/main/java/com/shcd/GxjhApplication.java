package com.shcd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.shcd.gxjh.dao")
public class GxjhApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(GxjhApplication.class, args);
    }
}
