package com.kilo.controller;

import com.kilo.domain.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kilo on 2018/4/25.
 */
@RestController
public class WebController {

    @RequestMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setName("小明");
        user.setAge(12);
        user.setPass("123");
        return user;
    }

    @RequestMapping("/getUsers")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setName("kilo");
        user1.setAge(30);
        user1.setPass("123456");
        users.add(user1);
        User user2 = new User();
        user2.setName("knightly");
        user2.setAge(18);
        user2.setPass("123");
        users.add(user2);
        return users;
    }

    /**
     * 使用Url进行传参
     * @param name
     * @return
     */
    @RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
    public User get(@PathVariable String name) {
        User user = new User();
        user.setName(name);
        return user;
    }

    @RequestMapping("/saveUser")
    public User saveUser(@Valid User user, BindingResult result){
        System.out.println("user:"+user);
        if (result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error:list) {
                System.out.println(error.getCode()+"-"+error.getDefaultMessage());
            }
        }
        return user;
    }
}
