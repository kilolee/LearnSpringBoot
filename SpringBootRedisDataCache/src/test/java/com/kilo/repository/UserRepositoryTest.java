package com.kilo.repository;

import com.kilo.entity.User;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by kilo on 2018/5/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    public void test(){
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userRepository.save(new User("aa","123","aa@qq.com","王尼玛",formattedDate));
        userRepository.save(new User("bb","123","bb@qq.com","王蜜桃",formattedDate));
        userRepository.save(new User("cc","123","cc@qq.com","李铁柱",formattedDate));

        Assert.assertEquals(3, userRepository.findAll().size());
        Assert.assertEquals("bb", userRepository.findByUserNameOrEmail("bb", "bb@qq.com").getNickName());
        userRepository.delete(userRepository.findByUserName("aa"));

    }


}