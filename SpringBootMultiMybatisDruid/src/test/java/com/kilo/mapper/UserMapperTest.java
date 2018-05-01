package com.kilo.mapper;

import com.kilo.entity.UserEntity;
import com.kilo.enums.UserSexEnum;
import com.kilo.mapper.one.UserOneMapper;
import com.kilo.mapper.two.UserTwoMapper;
import com.kilo.param.UserParam;
import com.kilo.result.Page;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by kilo on 2018/5/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserOneMapper userOneMapper;

    @Autowired
    private UserTwoMapper userTwoMapper;

    @Test
    public void testInsert() throws Exception {
        userOneMapper.insert(new UserEntity("aaa", "a123456", UserSexEnum.MAN));
        userOneMapper.insert(new UserEntity("bbb", "b123456", UserSexEnum.WOMAN));
        userTwoMapper.insert(new UserEntity("ccc", "b123456", UserSexEnum.WOMAN));

        Assert.assertEquals(2, userOneMapper.getAll().size());
        Assert.assertEquals(2, userOneMapper.getAll().size());
    }

    @Test
    public void testQuery() throws Exception {
        List<UserEntity> users = userTwoMapper.getAll();
        System.out.println(users.toString());
    }


    @Test
    public void testUpdate() throws Exception {
        UserEntity user = userOneMapper.getOne(34l);
        System.out.println(user.toString());
        user.setNickName("kilo");
        userOneMapper.update(user);
        Assert.assertTrue(("kilo".equals(userOneMapper.getOne(34l).getNickName())));
    }


    @Test
    public void testPage() {
        UserParam userParam=new UserParam();
        userParam.setUserSex("WOMAN");
        userParam.setCurrentPage(1);
        List<UserEntity> users=userOneMapper.getList(userParam);
        long count=userOneMapper.getCount(userParam);
        Page page = new Page(userParam,count,users);
        System.out.println(page);
    }






}
