package com.wind.springsession.aop;

import com.wind.springsession.annotation.AuthUser;
import com.wind.springsession.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Aspect
@Component
public class AuthUserAop {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserAop.class);

    @Resource
    private UserService userService;

    @Around("within(com.wind.springsession.controller..*) && @annotation(authUser)")
    public Object AuthUser(ProceedingJoinPoint proceedingJoinPoint, AuthUser authUser) throws Throwable {

        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        HttpServletResponse response = sra.getResponse();

        logger.info("Aop鉴权");

        boolean userNull = userService.userSessionIsNull(request);

        if(userNull){
            userService.authFail(response);
            return null;
        }

        return proceedingJoinPoint.proceed();
    }
}
