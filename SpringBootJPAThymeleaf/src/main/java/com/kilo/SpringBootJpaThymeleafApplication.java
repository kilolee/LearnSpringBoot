package com.kilo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SpringBootJpaThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaThymeleafApplication.class, args);
	}
}
