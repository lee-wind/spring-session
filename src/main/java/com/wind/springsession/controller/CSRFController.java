//package com.wind.springsession.controller;
//
//import com.wind.springsession.pojo.CSRF;
//import com.wind.springsession.util.CSRFUtil;
//import com.wind.springsession.util.SessionUtil;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.util.Date;
//
//@RestController
//@RequestMapping("/CSRF")
//public class CSRFController {
//
//    @Resource
//    private CSRFUtil csrfUtil;
//
//    @GetMapping("/get")
//    public CSRF getCSRF(HttpServletRequest request){
//
//        String key = SessionUtil.getUserId(request) + CSRFUtil.SUFFIX;
//
//        String value = String.valueOf(new Date().getTime());
//
//        return csrfUtil.create(key, value);
//    }
//
//    @PostMapping("/delete")
//    public void deleteCSRF(HttpServletRequest request){
//
//        String key = SessionUtil.getUserId(request) + CSRFUtil.SUFFIX;
//
//        csrfUtil.delete(key);
//    }
//}
