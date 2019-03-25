package com.wind.springsession.config;

import com.wind.springsession.interceptor.AuthInterceptor;
import com.wind.springsession.util.ServerConfigUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){

        registry.addInterceptor(authInterceptor).excludePathPatterns("/public/**", "/lib/**", "/user/login", "/user/register", "/user/logout", "/user/getAllUsers");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry){
//
//
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "POST")
//                .allowCredentials(true);
//    }
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry){
//
////        registry.addViewController("/error").setViewName("/static/public/error/404.html");
//    }
}
