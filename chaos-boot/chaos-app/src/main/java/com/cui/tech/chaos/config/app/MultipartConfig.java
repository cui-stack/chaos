package com.cui.tech.chaos.config.app;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class MultipartConfig {

    /**
     * 文件上传配置
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 文件最大
        //factory.setMaxFileSize("100MB"); // KB,MB
        /// 设置总上传数据总大小
        //factory.setMaxRequestSize("1000MB");
        return factory.createMultipartConfig();
    }

}
