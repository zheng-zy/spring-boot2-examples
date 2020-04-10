package com.zzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringBoot2FeginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2FeginApplication.class, args);
    }

}
