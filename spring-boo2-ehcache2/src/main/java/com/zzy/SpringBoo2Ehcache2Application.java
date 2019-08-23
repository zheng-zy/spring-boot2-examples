package com.zzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBoo2Ehcache2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoo2Ehcache2Application.class, args);
    }

}
