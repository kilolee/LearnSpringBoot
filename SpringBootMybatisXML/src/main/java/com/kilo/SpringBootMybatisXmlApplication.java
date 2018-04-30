package com.kilo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kilo.mapper")//Spring Boot 启动的时候会自动加载包路径下的 Mapper
public class SpringBootMybatisXmlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisXmlApplication.class, args);
    }
}
