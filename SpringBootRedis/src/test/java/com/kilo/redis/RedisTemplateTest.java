package com.kilo.redis;

import com.kilo.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by kilo on 2018/5/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString() {
        redisTemplate.opsForValue().set("kilo", "ityouknow");
        redisTemplate.opsForValue().set("kilo", "hello");
        Assert.assertEquals("ityouknow", redisTemplate.opsForValue().get("kilo"));
    }

    @Test
    public void testObj() {
        User user = new User("kilo", "123", "kilo@126.com", "knightly", "2018");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("com.kilo", user);
        User u = operations.get("com.kilo");
        System.out.println("user: " + u.toString());
    }

    @Test
    public void testExpire() throws InterruptedException {
        User user = new User("kiloExpire", "456", "kilo@qq.com", "expire", "2019");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("expire", user, 100, TimeUnit.MILLISECONDS);
        Thread.sleep(1000);
        boolean exists = redisTemplate.hasKey("expire");
        if (exists) {
            System.out.println("exists is true");
        } else {
            System.out.println("exists is false");
        }
    }

    @Test
    public void testDelete() {
        redisTemplate.opsForValue().set("deletekey", "ityouknow");
        redisTemplate.delete("deletekey");
        boolean exists = redisTemplate.hasKey("deletekey");
        if (exists) {
            System.out.println("exists is true");
        } else {
            System.out.println("exists is false");
        }
    }

    @Test
    public void testHash() {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put("hash", "you", "youknow");
        String value = (String) hash.get("hash", "you");
        System.out.println("hash value : " + value);
    }

    @Test
    public void testList() {
        String key = "list";
        redisTemplate.delete(key);
        ListOperations<String, String> list = redisTemplate.opsForList();
        list.leftPush(key, "it");
        list.leftPush(key, "you");
        list.leftPush(key, "know");
        String value = list.leftPop(key);
        System.out.println("list value : " + value);

        List<String> values = list.range(key, 0, 3);
        for (String v : values) {
            System.out.println("list range : " + v);
        }
    }

    @Test
    public void testSet() {
        String key = "set";
        redisTemplate.delete(key);
        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add(key, "it");
        set.add(key, "you");
        set.add(key, "you");
        set.add(key, "know");
        Set<String> values = set.members(key);
        for (String v : values) {
            System.out.println("set value : " + v);
        }
    }

    @Test
    public void testSetMore() {
        SetOperations<String, String> set = redisTemplate.opsForSet();
        String key1 = "setMore1";
        String key2 = "setMore2";
        set.add(key1, "it");
        set.add(key1, "you");
        set.add(key1, "you");
        set.add(key1, "know");
        set.add(key2, "xx");
        set.add(key2, "know");
        Set<String> diffs = set.difference(key1, key2);
        for (String v : diffs) {
            System.out.println("diffs set value : " + v);
        }

        String key3 = "setMore3";
        String key4 = "setMore4";
        set.add(key3, "it");
        set.add(key3, "you");
        set.add(key3, "xx");
        set.add(key4, "aa");
        set.add(key4, "bb");
        set.add(key4, "know");
        Set<String> unions = set.union(key3, key4);
        for (String v : unions) {
            System.out.println("unions value :" + v);
        }
    }

    /**
     * Redis sorted set 的使用场景与 set 类似，
     * 区别是 set 不是自动有序的，而 sorted set 可以通过用户额外提供一个优先级（score）的参数来为成员排序，
     * 并且是插入有序，即自动排序。
     */
    @Test
    public void testZset() {
        String key = "zset";
        redisTemplate.delete(key);
        ZSetOperations<String, String> zset = redisTemplate.opsForZSet();
        zset.add(key, "it", 1);
        zset.add(key, "you", 6);
        zset.add(key, "know", 4);
        zset.add(key, "kilo", 3);

        Set<String> zsets = zset.range(key, 0, 3);
        for (String v : zsets) {
            System.out.println("zset value : " + v);
        }

        Set<String> zsetB = zset.rangeByScore(key, 0, 3);
        for (String v : zsetB) {
            System.out.println("zsetB value : " + v);
        }

    }

}
