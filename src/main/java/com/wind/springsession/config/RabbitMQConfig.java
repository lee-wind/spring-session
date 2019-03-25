//package com.wind.springsession.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//
//@Slf4j
//@Configuration
//@ConfigurationProperties(prefix = "spring.rabbitmq")
//public class RabbitMQConfig {
//
//    final static String queueName = "queue.demo";
//
//    @Bean
//    Queue queue(){
//
//        return new Queue(queueName, true);
//    }
//
//    @Bean
//    public TopicExchange topicExchange(){
//
//        return new TopicExchange(
//                "spring-boot-topicExchange");
//    }
//
//    @Bean
//    public Binding binding(Queue queue, TopicExchange topicExchange){
//
//        return BindingBuilder.bind(queue).to(topicExchange).with("queue.#");
//    }
//
////    public static String host;
////    public static String port;
////    public static String username;
////    public static String password;
////
////    @Value("host")
////    public void setHost(String host) {
////        RabbitMQConfig.host = host;
////    }
////
////    @Value("port")
////    public void setPort(String port) {
////        RabbitMQConfig.port = port;
////    }
////
////    @Value("username")
////    public void setUsername(String username) {
////        RabbitMQConfig.username = username;
////    }
////
////    @Value("password")
////    public void setPassword(String password) {
////        RabbitMQConfig.password = password;
////    }
////
////    @PostConstruct
////    public void init(){
////
////        log.info("RabbitMQ host: {}", host);
////        log.info("RabbitMQ port: {}", port);
////        log.info("RabbitMQ username: {}", username);
////        log.info("RabbitMQ username: {}", username);
////    }
//}
