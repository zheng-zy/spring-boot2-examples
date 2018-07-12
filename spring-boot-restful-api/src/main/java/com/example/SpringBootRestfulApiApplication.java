package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.dao")
public class SpringBootRestfulApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestfulApiApplication.class, args);
	}
}
