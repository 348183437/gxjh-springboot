package com.cn.shcd.gxjh.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by zhh on 2020/2/22.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private CommonWebAuthInterceptor webAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webAuthInterceptor);
    }

}
