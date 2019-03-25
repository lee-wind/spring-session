package com.wind.springsession.config;

import com.wind.springsession.exception.CustomException;
import com.wind.springsession.exception.CustomRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
/*@ControllerAdvice*/
public class GlobalExceptionConfig {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionConfig.class);

    private static final String DEFAULT_EXCEPTION_VIEW = "/static/public/error.html";


    @ExceptionHandler(CustomException.class)
    public Map<String, String> CustomExceptionHandler(HttpServletRequest request, CustomException ce){

        Map<String, String> map = new HashMap<>();
        map.put("code", ce.getCode());
        map.put("message", ce.getMessage());
        return map;
    }

    @ExceptionHandler(CustomRuntimeException.class)
    public Map<String, String> CustomRuntimeExceptionHandler(HttpServletRequest request, CustomRuntimeException cre){

        Map<String, String> map = new HashMap<>();
        map.put("code", cre.getCode());
        map.put("message", cre.getMessage());
        return map;
    }

//    @ExceptionHandler(Exception.class)
//    public ModelAndView defaultExceptionHandler(HttpServletRequest request, Exception e) {
//
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName(DEFAULT_EXCEPTION_VIEW);
//        return mv;
//    }

}
