package com.theprovenclub.urlshorner.middleware;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestLoggingMiddleware implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggingMiddleware.class);

    @SuppressWarnings("null")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logRequest(request);
        return true;
    }

    private void logRequest(HttpServletRequest request) {
        String message = String.format("Incoming request: %s %s %s", request.getMethod(), request.getRequestURL(), LocalDateTime.now());
        LOGGER.info(message);

    }

    @SuppressWarnings("null")
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @SuppressWarnings("null")
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }




}
