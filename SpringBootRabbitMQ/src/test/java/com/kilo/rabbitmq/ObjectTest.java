package com.kilo.rabbitmq;

import com.kilo.model.User;
import com.kilo.object.ObjectSender;
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
public class ObjectTest {

    @Autowired
    private ObjectSender sender;

    @Test
    public void sendeObject() throws InterruptedException {
        User user = new User();
        user.setName("kilo");
        user.setPassword("123456");
        sender.send(user);
        Thread.sleep(1000);
    }


}
