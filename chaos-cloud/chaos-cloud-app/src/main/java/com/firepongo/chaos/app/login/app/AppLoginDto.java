package com.firepongo.chaos.app.login.app;

import com.firepongo.chaos.app.login.LoginDto;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * @author G.G
 * @date 2020/2/14 16:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppLoginDto extends LoginDto {
    @NotBlank(message = "账户不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}
