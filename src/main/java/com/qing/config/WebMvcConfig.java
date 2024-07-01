package com.qing.config;

import com.qing.intercept.HeadlineIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private HeadlineIntercepter headlineIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(headlineIntercepter).addPathPatterns("/headline/**");
    }
}
