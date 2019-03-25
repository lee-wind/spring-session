//package com.wind.springsession.webSocket;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import javax.websocket.*;
//import javax.websocket.server.ServerEndpoint;
//import java.util.concurrent.CopyOnWriteArraySet;
//
//@Slf4j
//@ServerEndpoint("/ws/chat")
//@Component
//public class WebSocket {
//
//    @Resource
//    private CachingConnectionFactory cachingConnectionFactory;
//
//    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
//    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();
//
//    //与某个客户端的连接会话，需要通过它来给客户端发送数据
//    private Session session;
//
//    @OnOpen
//    public void onOpen(Session session){
//
//        log.info(cachingConnectionFactory.getHost());
//    }
//
//    @OnClose
//    public void onClose(){
//
//    }
//
//    @OnMessage
//    public void onMessage(String message, Session session){
//
//    }
//
//    @OnError
//    public void onError(Session session, Throwable throwable){
//
//        throwable.printStackTrace();
//    }
//}
