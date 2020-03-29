package com.cn.shcd.gxjh.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.cn.shcd.gxjh.dao")
public class LpAccountApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(LpAccountApplication.class, args);
    }
}
