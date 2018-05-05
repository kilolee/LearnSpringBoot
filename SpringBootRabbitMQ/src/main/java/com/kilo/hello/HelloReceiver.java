package com.kilo.hello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 定义接收者
 * Created by kilo on 2018/5/5.
 */
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {

    //Annotation that marks a method to be the target of a Rabbit message
    @RabbitHandler
    public void process(String hello){
        System.out.println("Receiver : "+hello);
    }
}
