//package com.wind.springsession.interceptor;
//
//import com.wind.springsession.service.RedisSessionService;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
//import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;
//
//import javax.annotation.Resource;
//import java.security.Principal;
//
//@Component
//public class AuthWebSocketHandlerDecoratorFactory implements WebSocketHandlerDecoratorFactory {
//
//    @Resource
//    private RedisSessionService redisSessionService;
//
//    @Override
//    public WebSocketHandler decorate(WebSocketHandler webSocketHandler) {
//
//        return new WebSocketHandlerDecorator(webSocketHandler){
//
//            @Override
//            public void afterConnectionEstablished(final WebSocketSession webSocketSession)
//                    throws Exception{{
//
//                    Principal principal = webSocketSession.getPrincipal();
//
//                    if(principal != null){
//                        String username = principal.getName();
//                        redisSessionService.add(username, webSocketSession.getId());
//                    }
//
//                    super.afterConnectionEstablished(webSocketSession);
//                }
//            }
//
//            @Override
//            public void afterConnectionClosed(WebSocketSession webSocketSession,
//                                              CloseStatus closeStatus) throws Exception {
//
//                Principal principal = webSocketSession.getPrincipal();
//
//                if(principal != null){
//                    String username = webSocketSession.getPrincipal().getName();
//                    redisSessionService.delete(username);
//                }
//
//                super.afterConnectionClosed(webSocketSession, closeStatus);
//            }
//        };
//    }
//}
