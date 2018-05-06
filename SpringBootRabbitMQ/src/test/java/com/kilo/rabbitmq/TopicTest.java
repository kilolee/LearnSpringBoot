package com.kilo.rabbitmq;

import com.kilo.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by kilo on 2018/5/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicTest {
    @Autowired
    private TopicSender sender;

    @Test
    public void topic() throws InterruptedException {
        sender.send();
        Thread.sleep(1000);
    }

    @Test
    public void topic1() throws InterruptedException {
        sender.send1();
        Thread.sleep(1000);
    }

    @Test
    public void topic2() throws InterruptedException {
        sender.send2();
        Thread.sleep(1000);
    }


}
