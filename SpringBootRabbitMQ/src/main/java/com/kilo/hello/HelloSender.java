package com.kilo.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定义发送者
 * Created by kilo on 2018/5/5.
 */
@Component
public class HelloSender {

    //AmqpTemplate 是 Spring Boot 提供的默认实现。
    //指定一组基本的AMQP操作
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

}
