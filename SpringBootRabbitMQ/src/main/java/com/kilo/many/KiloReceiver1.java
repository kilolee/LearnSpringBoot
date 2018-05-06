package com.kilo.many;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by kilo on 2018/5/6.
 */
@Component
@RabbitListener(queues = "kilo")
public class KiloReceiver1 {

    @RabbitHandler
    public void process(String content){
        System.out.println("Receiver 1 : "+content);
    }
}
