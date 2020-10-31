package com.cui.tech.chaos.model.login;

import lombok.Data;

/**
 * @author G.G
 * @date 2020/2/14 16:34
 */
@Data
public class AppCodeLoginDto extends LoginDto {
    private String channel;
    private String code;
}
