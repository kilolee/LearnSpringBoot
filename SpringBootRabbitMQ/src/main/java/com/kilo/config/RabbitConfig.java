package com.kilo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定义队列
 * Created by kilo on 2018/5/5.
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    @Bean
    public Queue kiloQueue() {
        return new Queue("kilo");
    }

    @Bean
    public Queue objectQueue() {
        return new Queue("object");
    }


}
