package com.kilo.mapper;

import com.kilo.entity.UserEntity;
import com.kilo.enums.UserSexEnum;
import com.kilo.param.UserParam;
import com.kilo.result.Page;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kilo on 2018/4/30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testInsert() throws Exception {
        userMapper.insert(new UserEntity("dd", "d123456", UserSexEnum.MAN));
        userMapper.insert(new UserEntity("ee", "e123456", UserSexEnum.WOMAN));
        userMapper.insert(new UserEntity("ff", "f123456", UserSexEnum.WOMAN));

        Assert.assertEquals(3, userMapper.getAll().size());
    }

    @Test
    public void getQuery() throws Exception {

        List<UserEntity> users = userMapper.getAll();
        if (users == null || users.size() == 0) {
            System.out.println("is null");
        } else {
            System.out.println(users.toString());
        }
    }

    @Test
    public void update() throws Exception {
        long id = 30l;
        UserEntity user = userMapper.getOne(id);
        if (user != null) {
            System.out.println(user.toString());
            user.setNickName("kilo");
            userMapper.update(user);
            Assert.assertTrue(("kilo".equals(userMapper.getOne(id).getNickName())));
        } else {
            System.out.println("not find user id =" + id);
        }
    }

    @Test
    public void testDelete() throws Exception {
        int count = userMapper.delete(29l);
        if (count > 0) {
            System.out.println("delete is success");
        } else {
            System.out.println("delete is failed");
        }
    }

    @Test
    public void testPage() {
        UserParam userParam = new UserParam();
        userParam.setUserSex("WOMAN");
        userParam.setCurrentPage(2);
        List<UserEntity> users = userMapper.getList(userParam);
        long count = userMapper.getCount(userParam);
        Page page = new Page(userParam, count, users);
        System.out.println(page);
    }

}