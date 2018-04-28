package com.kilo.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kilo on 2018/4/25.
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String hello(String name) {
        return "Hello World "+name;
    }
}
