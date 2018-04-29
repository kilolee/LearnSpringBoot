package com.kilo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(
		exclude = {DataSourceAutoConfiguration.class}
)
public class SpringBootJpaThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaThymeleafApplication.class, args);
	}
}
