//package com.wind.springsession.interceptor;
//
//import com.wind.springsession.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.Map;
//
//@Slf4j
//@Component
//public class WsHandshakeInterceptor implements HandshakeInterceptor {
//
//    @Resource
//    UserService userService;
//
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest,
//                                   ServerHttpResponse serverHttpResponse,
//                                   WebSocketHandler webSocketHandler,
//                                   Map<String, Object> map)
//            throws Exception {
//
//        log.info("before http shake to webSocket: {}, {}",
//                this.getClass().getCanonicalName(), serverHttpRequest.getURI());
//
//
//        if(userService.userSessionIsNull((HttpServletRequest) serverHttpRequest)){
//            log.info("no user");
//            return false;
//        }
//
//        return true;
//    }
//
//    @Override
//    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
//
//        log.info("shake success: {}", this.getClass().getCanonicalName());
//    }
//}
