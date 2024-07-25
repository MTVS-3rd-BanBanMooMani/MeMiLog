package com.banbanmoomani.memilog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Autowired
    private StopWatchInterceptor stopWatchInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(stopWatchInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/error")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/user/**")
                .excludePathPatterns("/document/css/**")
                .excludePathPatterns("/document/js/**")
                .excludePathPatterns("/document/img/**")
                .excludePathPatterns("/fragments/**")
                .excludePathPatterns("/static/**", "/public/**", "/resources/**", "/META-INF/resources/**")
                .excludePathPatterns("/assets/**");
    }
}
