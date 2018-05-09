package com.kilo.template;

import com.kilo.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by kilo on 2018/5/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testSaveUser() {
        UserEntity user = new UserEntity();
        user.setId(2l);
        user.setUserName("小明");
        user.setPassWord("123456");
        userDao.saveUser(user);
    }

    @Test
    public void updateUser() {
        UserEntity user = new UserEntity();
        user.setId(2l);
        user.setUserName("天空");
        user.setPassWord("789");
        userDao.updateUser(user);
    }

    @Test
    public void findUser() {
        UserEntity user = userDao.findUserByUserName("小明");
        System.out.println(user);
    }

    @Test
    public void deleteUserById() {
        userDao.deleteUserById(2l);
    }
}
