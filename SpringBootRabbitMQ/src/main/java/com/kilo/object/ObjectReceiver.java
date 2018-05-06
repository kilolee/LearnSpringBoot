package com.kilo.object;

import com.kilo.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by kilo on 2018/5/6.
 */
@Component
@RabbitListener(queues = "object")
public class ObjectReceiver {

    @RabbitHandler
    public void process(User user){
        System.out.println("Receiver object : "+user);
    }
}
