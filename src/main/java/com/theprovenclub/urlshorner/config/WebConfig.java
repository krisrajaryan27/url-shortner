package com.theprovenclub.urlshorner.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.theprovenclub.urlshorner.middleware.RequestLoggingMiddleware;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RequestLoggingMiddleware requestLoggingMiddleware;

    @SuppressWarnings("null")
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestLoggingMiddleware);
    }
}
