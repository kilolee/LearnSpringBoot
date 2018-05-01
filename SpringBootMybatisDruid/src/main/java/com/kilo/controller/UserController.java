package com.kilo.controller;

import com.kilo.entity.UserEntity;
import com.kilo.mapper.UserMapper;
import com.kilo.param.UserParam;
import com.kilo.result.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by kilo on 2018/5/1.
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/getUsers")
    public List<UserEntity> getUsers() {
        return userMapper.getAll();
    }

    @RequestMapping("/getList")
    public Page<UserEntity> getList(UserParam userParam) {
        List<UserEntity> users = userMapper.getList(userParam);
        long count = userMapper.getCount(userParam);
        Page page = new Page(userParam, count, users);
        return page;
    }

    @RequestMapping("/add")
    public void save(UserEntity user) {
        userMapper.insert(user);
    }

    @RequestMapping("/update")
    public void update(UserEntity user) {
        userMapper.update(user);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userMapper.delete(id);
    }

}
