package com.firepongo.chaos.manager;

import com.firepongo.chaos.web.service.admin.AdminPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.firepongo"})
public class ChaosManageApplication {
    @Autowired
    private AdminPlatformService adminPlatformService;

    public static void main(String[] args) {
        SpringApplication.run(ChaosManageApplication.class, args);
    }

    @PostConstruct
    public void init() {
        adminPlatformService.platformLogin();
    }
}
