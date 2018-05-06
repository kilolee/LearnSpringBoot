package com.kilo.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Topic Receiver 1
 * Created by kilo on 2018/5/6.
 */
@Component
@RabbitListener(queues = "topic.message")
public class TopicReceiver1 {

    @RabbitHandler
    public void process(String message) {
        System.out.println("Topic Receiver 1 : " + message);
    }
}
