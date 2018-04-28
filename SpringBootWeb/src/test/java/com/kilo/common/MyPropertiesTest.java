package com.kilo.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by kilo on 2018/4/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyPropertiesTest {

    @Autowired
    private MyProperties properties;

    @Test
    public void testProperties() {
        System.out.println("title: " + properties.getTitle());
        System.out.println("description: " + properties.getDescription());
    }
}