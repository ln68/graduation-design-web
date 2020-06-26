package com.example.graduationdesignweb;

import com.example.graduationdesignweb.interceptor.LoginInterceptor;
import com.example.graduationdesignweb.interceptor.TeacherInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private TeacherInterceptor teacherInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( loginInterceptor )
                .addPathPatterns( "/api/**" )
                .excludePathPatterns( "/api/login", "/api/test" );

        registry.addInterceptor( teacherInterceptor )
                .addPathPatterns( "/api/teacher/**" );
    }
}