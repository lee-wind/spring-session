package com.wind.springsession.aop;

import com.wind.springsession.annotation.CSRFAnnotation;
import com.wind.springsession.util.CSRFUtil;
import com.wind.springsession.util.JSONUtil;
import com.wind.springsession.util.ObjectUtil;
import com.wind.springsession.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class CSRFAop {

    @Resource
    private CSRFUtil csrfUtil;

    private static final String CHARSET = "UTF-8";
    private static byte[] CSRF_ERROR  = null;
    private static byte[] CSRF_RESULT  = null;

    static {
        Map<String, String> CSRFError = new HashMap<>();
        CSRFError.put("code", "error");
        CSRFError.put("message", "非法提交");

        Map<String, String> CSRFResult = new HashMap<>();
        CSRFResult.put("code", "error");
        CSRFResult.put("message", "非法提交");
        try {
            CSRF_ERROR = JSONUtil.toJSONString(CSRFError).getBytes(CHARSET);
            CSRF_RESULT = JSONUtil.toJSONString(CSRFResult).getBytes(CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Around("execution(* com.wind.springsession..*.*(..)) && @annotation(csrfAnnotation)")
    public Object CSRF(ProceedingJoinPoint joinPoint, CSRFAnnotation csrfAnnotation) throws Throwable {


        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        HttpServletResponse response = sra.getResponse();

        String _csrf = request.getParameter("CSRF");

        log.info("_csrf: {}", _csrf);

        if(ObjectUtil.isNull(_csrf)){
            OutputStream out = response.getOutputStream();
            out.write(CSRF_RESULT);
            out.flush();
            out.close();
            return null;
        }

        String CSRFKey = SessionUtil.getSessionString(request, SessionUtil.USER_SESSION_NAME) + CSRFUtil.SUFFIX;

        String CSRFValue = csrfUtil.get(CSRFKey);

        if(!_csrf.equals(CSRFValue)){
            OutputStream out = response.getOutputStream();
            out.write(CSRF_ERROR);
            out.flush();
            out.close();
            return null;
        }

        Object result = joinPoint.proceed();

        return result;
    }
}
