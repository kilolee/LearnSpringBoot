package com.kilo.controller;

import com.kilo.entity.User;
import com.kilo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by kilo on 2018/5/3.
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 缓存中的 key 就是参数 name，value 就是返回的 String 值。
     * @param name
     * @return
     */
    @RequestMapping("/hello")
    @Cacheable(value = "helloCache")
    public String hello(String name) {
        System.out.println("没有走缓存！");
        return "hello : " + name;
    }

    /**
     * 条件缓存
     * 只有满足condition条件的name才会加入缓存
     *
     * @param name
     * @return
     */
    @RequestMapping("/condition")
    @Cacheable(value = "condititon", condition = "#name.length() <=4")
    public String condition(String name) {
        System.out.println("没有走缓存！");
        return "hello : " + name;
    }


    /**
     * 需要注意的是当一个支持缓存的方法在对象内部被调用时是不会触发缓存功能的。
     *
     * @param nickName
     * @return
     */
    @RequestMapping("/getUsers")
    @Cacheable(value = "usersCache", key = "#nickName", condition = "#nickName.length() >=6")
    public List<User> getUsers(String nickName) {
        List<User> users = userRepository.findByNickName(nickName);
        System.out.println("执行了数据库操作");
        return users;
    }

    /**
     * @param nickName
     * @return
     * @CachePut不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法， 并将执行结果以键值对的形式存入指定的缓存中
     */
    @RequestMapping("/getPutUsers")
    @CachePut(value = "usersCache", key = "#nickName")
    public List<User> getPutUsers(String nickName) {
        List<User> users = userRepository.findByNickName(nickName);
        System.out.println("执行了数据库操作");
        return users;
    }


    @RequestMapping("/cleanCache")
    @CacheEvict(value = "usersCache", key = "#nickName")
    public String cleanCache(String nickName) {
        String msg = "清除了nickName为键的缓存";
        System.out.println(msg);
        return msg;
    }

    @RequestMapping("/allEntries")
    @CacheEvict(value = "usersCache", allEntries = true)
    public String allEntries() {
        String msg = "执行了allEntries";
        System.out.println(msg);
        return msg;
    }

    @RequestMapping("/beforeInvocation")
    @CacheEvict(value = "usersCache", allEntries = true, beforeInvocation = true)
    public void beforeInvocation() {
        throw new RuntimeException("test beforeInvocation");
    }


}
