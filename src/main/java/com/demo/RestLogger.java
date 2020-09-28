package com.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class RestLogger {

    @Autowired
    private HttpServletRequest request;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.demo.controller.*Controller.*(..))")
    public void pointcut(){}

    @AfterReturning(value = "pointcut()",  returning = "returnValue")
    public void after(JoinPoint joinPoint, Object returnValue) {
        logger.info("request ip: {}, request url: {}, http method: {}, name:{}, args: {} \n",
                request.getRemoteAddr(), request.getRequestURL(), request.getMethod(),
                joinPoint.getSignature().getName(), joinPoint.getArgs()
        );
        logger.debug("request ip: {}, request url: {}, http method: {}, name:{}, args: {}, result: {} \n",
                request.getRemoteAddr(), request.getRequestURL(), request.getMethod(),
                joinPoint.getSignature().getName(), joinPoint.getArgs(), returnValue
        );
    }
}