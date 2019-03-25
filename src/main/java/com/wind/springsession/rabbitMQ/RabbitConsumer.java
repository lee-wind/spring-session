//package com.wind.springsession.rabbitMQ;
//
//import com.rabbitmq.client.Channel;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Slf4j
//@Component
//public class RabbitConsumer{
//
//    @RabbitListener(queues = "queue.demo")
//    public void process(String msg,
//                        Message message,
//                        Channel channel){
//        log.info(msg);
//        log.info("{}", message);
//        try {
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
