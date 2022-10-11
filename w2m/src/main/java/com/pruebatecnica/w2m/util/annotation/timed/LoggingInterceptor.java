package com.pruebatecnica.w2m.util.annotation.timed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.ZonedDateTime;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (!handlerMethod.hasMethodAnnotation(CustomTimed.class)) {
            return true;
        }

        long startTime = ZonedDateTime.now().toInstant().toEpochMilli();
        request.setAttribute("executionTime", startTime);
        logger.info("[preHandle][" + request + "][handler: " + handler + "]");
        logger.info("executionTime [START] -> " + startTime);

        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        long startTime = (Long) request.getAttribute("executionTime");
        long endTime = ZonedDateTime.now().toInstant().toEpochMilli();
        long duration = endTime - startTime;
        logger.info("[afterCompletion][" + request + "][exception: " + ex + "]");
        logger.info("executionTime [END] -> " + endTime);
        logger.info("executionTime [DURATION] -> " + duration);

    }

}
