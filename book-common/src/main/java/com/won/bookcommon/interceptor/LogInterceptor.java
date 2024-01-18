package com.won.bookcommon.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String logId = UUID.randomUUID().toString().substring(0,8);
        request.setAttribute("logId", logId);

        log.info("REQUEST [{}][uri={}][{}]", logId, requestURI, handler);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String logId = UUID.randomUUID().toString().substring(0,8);
        request.setAttribute("logId", logId);
        log.info("REQUEST afterCompletion [{}][uri={}][{}]", logId, requestURI, handler);

        if (ex != null) {
            log.error("REQUEST ERROR! ", ex);
        }
    }
}
