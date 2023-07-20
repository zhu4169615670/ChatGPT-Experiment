package com.huilian.config;

import com.huilian.interceptor.ResourcesInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class LoginConifg implements  WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ResourcesInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/toLogin", "/toReg"
                        , "/login", "/reg",
                        "/assets/**","/images/**","/bootstrap-datetimepicker-master/**","/bootstrap/**","/datatable/**","/media/**,/style/**");
    }

    }
