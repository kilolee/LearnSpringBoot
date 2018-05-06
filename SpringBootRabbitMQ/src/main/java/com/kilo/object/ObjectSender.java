package com.kilo.object;

import com.kilo.model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kilo on 2018/5/6.
 */
@Component
public class ObjectSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(User user){
        System.out.println("Sender object : "+user);
        rabbitTemplate.convertAndSend("object",user);
    }




}
