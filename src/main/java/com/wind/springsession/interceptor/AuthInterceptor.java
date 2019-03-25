package com.wind.springsession.interceptor;

import com.wind.springsession.service.UserService;
import com.wind.springsession.util.ServerConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Resource
    private UserService userService;


    /*
     * 进入controller层之前拦截请求
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        logger.info("Interceptor鉴权");
        logger.info("request: {}", request);

        boolean userNull = userService.userSessionIsNull(request);

        if(!userNull){
            return true;
        }

        boolean canAutoLogin = userService.autoLogin(request, response);

        if(canAutoLogin){
            return true;
        }

        userService.authFail(response);

        return false;
    }


    /*
     * 处理请求完成后视图渲染之前的处理操作
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    /*
     * 视图渲染之后的操作
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }

}
