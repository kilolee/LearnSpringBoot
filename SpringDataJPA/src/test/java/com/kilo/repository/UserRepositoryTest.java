package com.kilo.repository;

import com.kilo.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;


import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kilo on 2018/4/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    //        @Test
    public void test() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);
//        System.out.println(dateFormat.format(date));

        userRepository.save(new User("aa", "aa123456", "aa@126.com", "aa", formattedDate));
        userRepository.save(new User("bb", "bb123456", "bb@126.com", "bb", formattedDate));
        userRepository.save(new User("cc", "cc123456", "cc@126.com", "cc", formattedDate));

//        Assert.assertEquals(9, userRepository.findAll().size());
//        Assert.assertEquals("bb", userRepository.findByUserNameAndEmail("bb", "bb@126.com").getNickName());
//        userRepository.delete(userRepository.findByUserName("aa"));
    }

    @Test
    public void testPageQuery() {
        //页数page从0开始
        int page = 0;
        int size = 2;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        Pageable pageable = new PageRequest(page, size, sort);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> usersPage = userRepository.findAll(pageable);
        Page<User> userPage = userRepository.findByNickName("bb", pageable);
    }

    @Test
    public void testComplexQuery() {
//        userRepository.deleteById(Long.valueOf(6));
        User user = userRepository.findByEmail("aa@126.com");
    }

}