//package com.wind.springsession.controller;
//
//
//import com.wind.springsession.pojo.Greeting;
//import com.wind.springsession.pojo.HelloMessage;
//import com.wind.springsession.pojo.ResponseMessage;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageHeaders;
//import org.springframework.messaging.handler.annotation.*;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.messaging.simp.annotation.SendToUser;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.util.HtmlUtils;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.security.Principal;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Controller
//@Slf4j
//public class WebSocketController {
//
//    private AtomicInteger count = new AtomicInteger(0);
//
//    @Resource
//    private AmqpTemplate amqpTemplate;
//
//    @Resource
//    private SimpMessagingTemplate simpMessagingTemplate;
//
//    @MessageMapping("/ch/{to}")
//    public void chat(
//            @DestinationVariable String to,
//            String msg,
//            Message message,
//            MessageHeaders messageHeaders,
//            @Header("destination") String destination,
//            @Headers Map<String, Object> headers,
//            @Payload String body) {
////        log.info("message:{}", message);
////        log.info("messageHeaders:{}", messageHeaders);
////        log.info("destination:{}", destination);
////        log.info("headers:{}", headers);
//////        log.info("id:{}", id);
////        log.info("body:{}", body);
//
//
//        simpMessagingTemplate.convertAndSendToUser("amp.topic",
//                "/topic/"+to,
//                 "-send: " + msg);
//    }
//
//    @MessageMapping("/chat")
//    @SendToUser("/topic/chat")
//    public String toSelf(
//                Principal principal,
//                       String message){
//
//        log.info(message);
//
//        return message;
//    }
//
//
//    @MessageMapping("/receive")
//    @SendTo("/topic/getResponse")
//    public ResponseMessage broadcast(String message){
//
//        ResponseMessage responseMessage = new ResponseMessage();
//        responseMessage.setResponseMessage("BroadcastCtl receive [" + count.incrementAndGet() + "] records : " + message);
//        return responseMessage;
//    }
//
//    @RequestMapping("/broadcast/index")
//    public String broadcastIndex(HttpServletRequest request){
//
//        log.info(request.getRemoteHost());
//        return "websocket/simple/ws-broadcast";
//    }
//
//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(HelloMessage message) throws Exception {
//        Thread.sleep(1000); // simulated delay
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }
//}
