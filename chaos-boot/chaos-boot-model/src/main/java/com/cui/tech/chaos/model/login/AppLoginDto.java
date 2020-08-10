package com.cui.tech.chaos.model.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author G.G
 * @date 2020/2/14 16:34
 */
@Data
public class AppLoginDto extends LoginDto {
    @NotBlank(message = "账户不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}
