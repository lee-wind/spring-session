//package com.wind.springsession.config;
//
//import com.wind.springsession.interceptor.AuthDefaultHandshakeHandler;
//import com.wind.springsession.interceptor.AuthHandshakeInterceptor;
//import com.wind.springsession.interceptor.AuthWebSocketHandlerDecoratorFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
//
//import javax.annotation.Resource;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class RabbitMQWebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {
//
//    @Resource
//    private AuthWebSocketHandlerDecoratorFactory authWebSocketHandlerDecoratorFactory;
//
//    @Resource
//    private AuthHandshakeInterceptor authHandshakeInterceptor;
//
//    @Resource
//    private AuthDefaultHandshakeHandler authDefaultHandshakeHandler;
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry){
//
//        registry.addEndpoint("/ws")
////                .addInterceptors(authHandshakeInterceptor)
////                .setHandshakeHandler(authDefaultHandshakeHandler)
//                .setAllowedOrigins("*")
//                .withSockJS();
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry){
//
//        registry.enableStompBrokerRelay(
//                "/exchange",
//                "/topic",
//                "/queue",
//                "/amq/queue")
//        .setRelayHost("localhost")
//        .setRelayPort(61613)
//        .setClientLogin("guest")
//        .setClientPasscode("guest");
//    }
//
////    @Override
////    public void configureWebSocketTransport(WebSocketTransportRegistration webSocketTransportRegistration){
////
////        webSocketTransportRegistration.addDecoratorFactory(authWebSocketHandlerDecoratorFactory);
////    }
//}
//
