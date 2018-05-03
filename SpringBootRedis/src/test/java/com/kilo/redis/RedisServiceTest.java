package com.kilo.redis;

import com.kilo.entity.User;
import com.kilo.service.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by kilo on 2018/5/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void testString() {
        redisService.set("kilo", "isService");
        Assert.assertEquals("isService", redisService.get("kilo"));
    }

    @Test
    public void testObj() {
        User user = new User("kilo01", "0123", "kilo01@qq.com", "smile", "1992");
        redisService.set("user", user);
        User u = (User) redisService.get("user");
        System.out.println(u);
    }

}
