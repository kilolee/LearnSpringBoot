package com.kilo.repository;

import com.kilo.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by kilo on 2018/5/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;


    @Test
    public void testSaveUser() {
        for (long i = 0; i < 100; i++) {
            UserEntity user = new UserEntity();
            user.setId(i);
            user.setUserName("小明" + i);
            user.setPassWord("123456789");
            userRepository.save(user);
        }
    }

    @Test
    public void saveUser() {
        UserEntity user = new UserEntity();
        user.setId(2l);
        user.setUserName("小明");
        user.setPassWord("123456");
        userRepository.save(user);
    }

    @Test
    public void findUserByUserName() {
        UserEntity user = userRepository.findByUserName("小明");
        System.out.println("User : " + user);
    }

    @Test
    public void updateUser() {
        UserEntity user = new UserEntity();
        user.setId(2l);
        user.setUserName("天空");
        user.setPassWord("456");
        userRepository.save(user);
    }

    @Test
    public void deleteUser() {
        userRepository.deleteById(2l);
    }

    @Test
    public void testPage() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(1, 10, sort);
        Page page = userRepository.findAll(pageable);
        System.out.println("users ：" + page.getContent().toString());
    }

}
