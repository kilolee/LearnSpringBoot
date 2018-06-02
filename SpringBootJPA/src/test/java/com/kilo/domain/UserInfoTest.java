package com.kilo.domain;

import com.kilo.repository.UserDetailRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kilo on 2018/4/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoTest {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Test
    public void testUserInfo() {
        List<UserInfo> userInfos = userDetailRepository.findUserInfo("打球");
        for (UserInfo userInfo :
                userInfos) {
            System.out.println("address: " + userInfo.getAddress());
        }
    }
}