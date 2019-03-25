//package com.wind.springsession.interceptor;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//import javax.servlet.http.HttpSession;
//import java.util.Map;
//
//@Slf4j
//@Component
//public class AuthHandshakeInterceptor implements HandshakeInterceptor {
//
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest,
//                                   ServerHttpResponse serverHttpResponse,
//                                   WebSocketHandler webSocketHandler,
//                                   Map<String, Object> map) throws Exception {
//
//        HttpSession session = getSession(serverHttpRequest);
//
//        if(session == null || session.getAttribute("userId") == null){
//
//            log.info("please login");
//
//            return false;
//        }
//
//        map.put("userId", session.getAttribute("userId"));
//
//        return true;
//    }
//
//    @Override
//    public void afterHandshake(ServerHttpRequest serverHttpRequest,
//                               ServerHttpResponse serverHttpResponse,
//                               WebSocketHandler webSocketHandler, Exception e) {
//
//    }
//
//    // 参考 HttpSessionHandshakeInterceptor
//    private HttpSession getSession(ServerHttpRequest request) {
//
//        if (request instanceof ServletServerHttpRequest) {
//
//            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
//
//            return serverRequest.getServletRequest().getSession(false);
//        }
//
//        return null;
//    }
//}
