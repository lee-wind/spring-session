package com.wind.springsession.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

@WebFilter(filterName = "XSSFilter", urlPatterns = "/*")
public class XSSFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(XSSFilter.class);

    @Override
    public void init(FilterConfig config) throws ServletException {

        logger.info("跨站脚本攻击过滤器开启！");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        XSSHttpServletRequestWrapper xssHttpServletRequestWrapper = new XSSHttpServletRequestWrapper((HttpServletRequest) req);

        chain.doFilter(xssHttpServletRequestWrapper, resp);
    }

    public void destroy() {

        logger.info("跨站脚本攻击过滤器销毁！");
    }


    class XSSHttpServletRequestWrapper extends HttpServletRequestWrapper {

        XSSHttpServletRequestWrapper(HttpServletRequest request) {

            super(request);
        }

//        @Override
//        public String getHeader(String name){
//
//            String value = super.getHeader(name);
//            return HtmlUtils.htmlEscape(value);
//        }

        @Override
        public String getParameter(String name) {

            String value = super.getParameter(name);
            return HtmlUtils.htmlEscape(value);
        }

        @Override
        public String[] getParameterValues(String name) {

            String[] values = super.getParameterValues(name);
            if (values != null) {
                int length = values.length;
                String[] escapeValues = new String[length];
                for (int i = 0; i < length; i++) {
                    escapeValues[i] = HtmlUtils.htmlEscape(values[i]);
                }
                return escapeValues;
            }
            return super.getParameterValues(name);
        }
    }
}