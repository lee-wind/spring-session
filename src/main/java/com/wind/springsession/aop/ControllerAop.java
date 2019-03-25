package com.wind.springsession.aop;

import com.wind.springsession.util.JSONUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
@Component
public class ControllerAop {

    private static final Logger log = LoggerFactory.getLogger(ControllerAop.class);

    private static final String JSON_CONTENT_TYPE = "application/json";

    @Pointcut("within(com.wind.springsession.controller..*)")
    public void pointcut(){}

    @Before("pointcut()")
    public void logRequestParameters(JoinPoint joinPoint){

        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        log.info("sra: {}", sra);
        HttpServletRequest request = sra.getRequest();

        log.info("请求方法：{}, url: {}", joinPoint.getSignature().toShortString(),
                request.getRequestURL());

        String contentType = request.getContentType();
        log.info("ContentType：{}", contentType);

        if(contentType != null && contentType.contains(JSON_CONTENT_TYPE)){

            log.info("JSON字符串参数：{}", JSONUtil.toJSONString(joinPoint.getArgs()));
            return;
        }

        Enumeration requestParameters = request.getParameterNames();

        while(requestParameters.hasMoreElements()){
            String parameterName = (String) requestParameters.nextElement();
            String[] parameterValues = request.getParameterValues(parameterName);
            if(parameterValues.length == 1){
                String parameterValue = parameterValues[0];
                log.info("参数名：{}, 参数值：{}", parameterName, parameterValue);
            }else{
                log.info("参数名：{}, 参数值：{}", parameterName, JSONUtil.toJSONString(parameterValues));
            }
        }
    }
}
