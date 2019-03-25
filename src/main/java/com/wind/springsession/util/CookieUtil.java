package com.wind.springsession.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieUtil {

    private static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);

    public static final String USER_COOKIE_DOMAIN = "wind.com";
    public static final String USER_COOKIE_PATH = "/";
    public static final String USER_COOKIE_NAME = "username";
    public static final String USER_COOKIE_KEY = "key";
    public static final int USER_COOKIE_MAX_AGE = 7 * 24 * 60 * 60;

    public static void addCookie(HttpServletResponse response, String name,
                                 String value){

        Cookie cookie = null;
        try {
            cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        response.addCookie(cookie);
    }

    public static void addCookie(HttpServletResponse response, String name,
                                 String value, int maxAge){

        Cookie cookie = new Cookie(name, value);

        cookie.setMaxAge(maxAge);

        response.addCookie(cookie);
    }

    public static void addCookie(HttpServletResponse response, String name,
                                 String value, int maxAge, String path){

        Cookie cookie = new Cookie(name, value);

        cookie.setMaxAge(maxAge);
        cookie.setPath(path);

        response.addCookie(cookie);
    }

    public static void addCookie(HttpServletResponse response, String name,
                                 String value, int maxAge, String domain, String path){

        Cookie cookie = new Cookie(name, value);

        cookie.setMaxAge(maxAge);
        cookie.setDomain(domain);
        cookie.setPath(path);

        response.addCookie(cookie);
    }

    public static void addCookie(HttpServletResponse response, String name,
                                 String value, int maxAge, boolean httpOnly){

        Cookie cookie = new Cookie(name, value);

        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(httpOnly);

        response.addCookie(cookie);
    }


    public static void addCookie(HttpServletResponse response, String name,
                                 String value, int maxAge, String path,
                                 boolean httpOnly){

        Cookie cookie = new Cookie(name, value);

        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        cookie.setHttpOnly(httpOnly);

        response.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest request, String name){

        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals(name)){
                    return cookie;
                }
            }
        }
        return null;
    }

    public static String getCookieValue(HttpServletRequest request, String name){

        Cookie cookie = getCookie(request, name);

        if(cookie != null){
            try {
                return URLDecoder.decode(cookie.getValue(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void removeCookie(HttpServletRequest request, HttpServletResponse response,
                                    String name){

        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals(name)){
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    return;
                }
            }
        }
    }

    public static void removeCookie(HttpServletRequest request, HttpServletResponse response,
                                    String name, String domain, String path){

        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals(name)){
                    logger.info("cookieName: {}", name);
                    cookie.setMaxAge(0);
                    cookie.setDomain(domain);
                    cookie.setPath(path);
                    response.addCookie(cookie);
                    return;
                }
            }
        }
    }
}
