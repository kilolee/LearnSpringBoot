package com.kilo.rabbitmq;

import com.kilo.many.KiloSender1;
import com.kilo.many.KiloSender2;
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
public class ManyTest {

    @Autowired
    private KiloSender1 kiloSender1;

    @Autowired
    private KiloSender2 kiloSender2;

    @Test
    public void oneToMany() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            kiloSender1.send(i);
        }
        Thread.sleep(10000);
    }

    @Test
    public void manyToMany() throws InterruptedException {
        for (int i =0;i<100;i++){
            kiloSender1.send(i);
            kiloSender2.send(i);
        }
        Thread.sleep(10000);
    }


}
