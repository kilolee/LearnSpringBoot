package com.kilo.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Topic Sender
 * Created by kilo on 2018/5/6.
 */
@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String content = "hi, i am message all";
        System.out.println("Sender : " + content);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.1", content);
    }

    public void send1() {
        String content = "hi, i am message 1";
        System.out.println("Sender 1 : " + content);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.message", content);
    }

    public void send2() {
        String content = "hi, i am message 2";
        System.out.println("Sender 2 : " + content);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.messages", content);
    }


}
