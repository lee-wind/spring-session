package com.wind.springsession.rabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class Sender {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public static final String SIMPLE_QUEUE = "tea";

    public static final  String FANOUT_EXCHANGE = "fanout_exchange";

    public static final String DIRECT_EXCHANGE = "direct_exchange";

    public static final String TOPIC_EXCHANGE = "topic_exchange";

    public void send(String message){

        this.rabbitTemplate.convertAndSend(SIMPLE_QUEUE, message);
    }

    public void sendMapMessage(Map map){

        this.rabbitTemplate.convertAndSend(SIMPLE_QUEUE, map);
    }

    public void psSend(String message){

        this.rabbitTemplate.convertAndSend(FANOUT_EXCHANGE, "", message);
    }

    public void routingSend(String routingKey, String message){

        this.rabbitTemplate.convertAndSend(DIRECT_EXCHANGE, routingKey, message);
    }

    public void topicSend(String routingKey, String message){

        this.rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, routingKey, message);
    }
}
