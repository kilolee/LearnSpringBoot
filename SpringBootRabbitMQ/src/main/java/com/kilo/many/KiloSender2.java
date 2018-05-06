package com.kilo.many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kilo on 2018/5/6.
 */
@Component
public class KiloSender2 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int i) {
        String content = "spring boot kilo queue" + "*******************" + i;
        System.out.println("Sender 2 : " + content);
        this.rabbitTemplate.convertAndSend("kilo", content);
    }

}
