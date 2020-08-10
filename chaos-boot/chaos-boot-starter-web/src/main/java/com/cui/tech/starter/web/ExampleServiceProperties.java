package com.cui.tech.starter.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author G.G
 * @date 2019/12/8 17:31
 */
@Data
@ConfigurationProperties("example.service")
public class ExampleServiceProperties {
    private String prefix;
    private String suffix;
}
