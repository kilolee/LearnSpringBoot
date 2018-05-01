package com.kilo.controller;

import com.kilo.entity.UserEntity;
import com.kilo.mapper.one.UserOneMapper;
import com.kilo.mapper.two.UserTwoMapper;
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
    private UserOneMapper userOneMapper;

    @Autowired
    private UserTwoMapper userTwoMapper;

    @RequestMapping("/getUsers")
    public List<UserEntity> getUsers() {
        List<UserEntity> usersOne = userOneMapper.getAll();
        List<UserEntity> usersTwo = userTwoMapper.getAll();
        usersOne.addAll(usersTwo);
        return usersOne;
    }

    @RequestMapping("/getList")
    public Page<UserEntity> getList(UserParam userParam) {
        List<UserEntity> users = userOneMapper.getList(userParam);
        long count = userOneMapper.getCount(userParam);
        Page page = new Page(userParam, count, users);
        return page;
    }

    @RequestMapping("/getUser")
    public UserEntity getUser(Long id) {
        UserEntity user = userTwoMapper.getOne(id);
        return user;
    }

    @RequestMapping("/add")
    public void save(UserEntity user) {
        userOneMapper.insert(user);
    }

    @RequestMapping(value = "update")
    public void update(UserEntity user) {
        userOneMapper.update(user);
    }

    @RequestMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userTwoMapper.delete(id);
    }

}
