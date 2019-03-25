//package com.wind.springsession.rabbitMQ;
//
//import com.rabbitmq.client.ConfirmCallback;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.support.CorrelationData;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class RabbitMQConfirmCallback implements RabbitTemplate.ConfirmCallback {
//
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//
//        log.info("correlationData: {}, ack: {}, cause: {}",
//                correlationData, ack, cause);
//    }
//}
