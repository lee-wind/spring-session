package com.wind.springsession.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class RabbitMQController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/rabbit")
    public void send(){

        String sendMsg = "Hello " + new Date();
        rabbitTemplate.convertAndSend(
                "spring-boot-topicExchange",
                "queue.demo",
                sendMsg);
    }
}
