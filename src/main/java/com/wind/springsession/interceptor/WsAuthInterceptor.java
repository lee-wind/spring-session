//package com.wind.springsession.interceptor;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.lang.Nullable;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.messaging.simp.stomp.StompCommand;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.messaging.support.ChannelInterceptor;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.security.Principal;
//
//@Component
//@Slf4j
//public class WsAuthInterceptor implements ChannelInterceptor {
//
//    @Resource
//    private SimpMessagingTemplate simpMessagingTemplate;
//
//    @Override
//    public Message<?> preSend(Message<?> message, MessageChannel channel){
//
//        log.info("{}: preSend", this.getClass().getCanonicalName());
//
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//        StompCommand command = accessor.getCommand();
//        if(StompCommand.SUBSCRIBE.equals(command)){
//            log.info("{}, subscribe destination: {}",
//                    this.getClass().getCanonicalName(),
//                    accessor.getDestination());
//
//            //accessor.getSessionAttributes().get()
//
//            return message;
//        }
//        return null;
//    }
//
//    public void afterSendCompletion(Message<?> message, MessageChannel channel,
//                                    boolean sent, @Nullable Exception ex) {
//
//        log.info("{}: afterSendCompletion", this.getClass().getCanonicalName());
//
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//        StompCommand command = accessor.getCommand();
//        if(StompCommand.SUBSCRIBE.equals(command)){
//            log.info("{}, subscribe message send success: {}",
//                    this.getClass().getCanonicalName());
//
//        }
//        if(StompCommand.DISCONNECT.equals(command)){
//            log.info("{}, user disconnect");
//        }
//    }
//
//}
