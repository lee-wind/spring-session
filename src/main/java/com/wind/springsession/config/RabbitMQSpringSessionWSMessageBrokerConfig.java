//package com.wind.springsession.config;
//
//import com.wind.springsession.interceptor.AuthHandshakeInterceptor;
//import com.wind.springsession.model.WebSocketUser;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.simp.config.ChannelRegistration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.messaging.simp.stomp.StompCommand;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.messaging.support.ChannelInterceptor;
//import org.springframework.session.Session;
//import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
//
//import javax.annotation.Resource;
//import java.security.Principal;
//import java.util.List;
//import java.util.Map;
//
//@Slf4j
//@Configuration
//@EnableWebSocketMessageBroker
//public class RabbitMQSpringSessionWSMessageBrokerConfig extends
//        AbstractSessionWebSocketMessageBrokerConfigurer<Session> {
//
//    @Resource
//    private AuthHandshakeInterceptor authHandshakeInterceptor;
//
//    @Override
//    protected void configureStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
//
//        stompEndpointRegistry.addEndpoint("/ws")
//                .addInterceptors(authHandshakeInterceptor)
////                .setHandshakeHandler(new DefaultHandshakeHandler(){
////
////                    protected Principal determineUser(ServerHttpRequest request,
////                                                      WebSocketHandler wsHandler,
////                                                      Map<String, Object> attributes) {
////
////                        return new WebSocketUser(String.valueOf(attributes.get("userId")));
////                    }
////                })
//                .setAllowedOrigins("*")
//                .withSockJS();
//    }
//
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//
//        registration.interceptors(new ChannelInterceptor() {
//
//            @Override
//            public Message<?> preSend(Message<?> message,
//                                      MessageChannel channel) {
//
//                StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//                String userId = String.valueOf(accessor.getSessionAttributes().get("userId"));
//
//                StompCommand command = accessor.getCommand();
//
//                log.info("canonicalName: {}, destination: {}",
//                        this.getClass().getCanonicalName(),
//                        accessor.getDestination());
//
//                log.info("sessionId: {}", accessor.getSessionId());
//                log.info("userId: {}", userId);
//
//                //log.info("userId: {}, send: {}", userId, message);
//
//                return message;
//            }
//        });
//    }
//
//    @Override
//    public void configureClientOutboundChannel(ChannelRegistration registration) {
//
//        registration.interceptors(new ChannelInterceptor() {
//            @Override
//            public Message<?> preSend(Message<?> message, MessageChannel channel) {
//
//                //log.info("receive: {}", message);
//
//                return message;
//            }
//        });
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//
//        //registry.enableSimpleBroker("/topic", "/queue");
//
//        //registry.setApplicationDestinationPrefixes("/app");
//        registry.setUserDestinationPrefix("/user");
//
//        registry.enableStompBrokerRelay("/exchange",
//                "/queue",
//                "/amp/queue",
//                "/topic",
//                "/temp-queue")
//                .setRelayHost("localhost")
//                .setRelayPort(61613)
//                .setClientLogin("guest")
//                .setClientPasscode("guest")
//                .setSystemHeartbeatSendInterval(4000)
//                .setSystemHeartbeatReceiveInterval(4000);
//    }
//}
//
