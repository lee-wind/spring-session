package com.wind.springsession.filter;

import com.wind.springsession.util.ServerConfigUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebFilter(filterName = "CORSFilter", urlPatterns = "/*")
@Slf4j
public class CORSFilter implements Filter {

    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUE = "true";
    private static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    private static final String ACCESS_CONTROL_ALLOW_METHODS_VALUE = "GET, POST";
    private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    private static final String ACCESS_CONTROL_ALLOW_HEADERS_VALUE = "Content-Type, Authorization";
    private static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
    private static final String ACCESS_CONTROL_MAX_AGE_VALUE = "1800"; //30MIN


    @Override
    public void init(FilterConfig config) throws ServletException {

        log.info("CORS过滤器开启！");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        log.info("request method: {}", request.getMethod());

        if(ServerConfigUtil.allowCORS){
            response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, request.getHeader("Origin"));
            response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUE);
            response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, ACCESS_CONTROL_ALLOW_METHODS_VALUE);
            response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, ACCESS_CONTROL_ALLOW_HEADERS_VALUE);
            //response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, request.getHeader("Access-Control-Request-Headers"));
        }

        if (request.getMethod().equals("OPTIONS")) {

            response.setHeader(ACCESS_CONTROL_MAX_AGE, ACCESS_CONTROL_MAX_AGE_VALUE);
            //response.setStatus(200);
            return;
        }

        chain.doFilter(request, response);
    }

    public void destroy() {

        log.info("CORS过滤器销毁！");
    }


}