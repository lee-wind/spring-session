package com.wind.springsession.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class SessionUtil {

    public static final String USER_SESSION_NAME = "userId";

    public static String getSessionString(HttpServletRequest request, String sessionName){

        HttpSession session = request.getSession();

        Object sessionValue = session.getAttribute(sessionName);

        if(sessionValue == null){
            return null;
        }
        return sessionValue.toString();

    }

    public static Object getSession(HttpServletRequest request, String sessionName){

        HttpSession session = request.getSession();

        return session.getAttribute(sessionName);

    }

    public static <E> void setSession(HttpServletRequest request, String sessionName,
                                      E sessionValue){

        HttpSession session = request.getSession(true);

        session.setAttribute(sessionName, sessionValue);
    }

    public static <E> void setSession(HttpServletRequest request, String sessionName,
                                      E sessionValue, int maxInactiveInterval){

        HttpSession session = request.getSession();

        session.setAttribute(sessionName, sessionValue);
        session.setMaxInactiveInterval(maxInactiveInterval);
    }

    public static void removeSession(HttpServletRequest request, String sessionName){

        HttpSession session = request.getSession();

        session.removeAttribute(sessionName);
    }

    public static String getUserId(HttpServletRequest request){


        return getSessionString(request, SessionUtil.USER_SESSION_NAME);
    }
}
