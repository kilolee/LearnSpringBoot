package com.kilo.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kilo on 2018/5/6.
 */
@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;


    public void send() {
        String content = "hi, fanout msg";
        System.out.println("Sender : " + content);
        this.rabbitTemplate.convertAndSend("fanoutExchange", "", content);
    }


}
