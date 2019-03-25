package com.wind.springsession.aop;

import com.wind.springsession.annotation.PrintSqlTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class SqlAop {

    private static final Logger logger = LoggerFactory.getLogger(SqlAop.class);

    @Around("execution(* com.wind.springsession..*.*(..)) && @annotation(printSqlTime)")
    public Object PrintSqlTime(ProceedingJoinPoint joinPoint, PrintSqlTime printSqlTime) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();

        logger.info("method: {}, executed time: {}s",
                joinPoint.getSignature().toShortString(),
                stopWatch.getTotalTimeSeconds());

        return result;
    }
}
