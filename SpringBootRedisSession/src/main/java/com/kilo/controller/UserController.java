package com.kilo.controller;

import com.kilo.entity.User;
import com.kilo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kilo on 2018/5/4.
 */
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/setSession")
    public Map<String, Object> setSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("message", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return map;
    }

    @RequestMapping("/getSession")
    public Object getSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("message"));
        return map;
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        String msg = "index content";
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            msg = "please login first !";
        }
        return msg;
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, String userName, String password) {
        String msg = "login failure";
        User user = userRepository.findByUserName(userName);
        if (user != null && user.getPassword().equals(password)) {
            request.getSession().setAttribute("user", user);
            msg = "login successful !";
        }
        return msg;
    }

}
