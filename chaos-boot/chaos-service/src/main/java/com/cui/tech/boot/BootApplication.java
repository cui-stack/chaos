package com.cui.tech.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.cui.tech.boot.service.mapper")
@ComponentScan(basePackages = {"com.cui.tech.chaos.lite", "com.cui.tech.boot"})
public class BootApplication {

public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
}
