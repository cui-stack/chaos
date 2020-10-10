package com.cui.tech.chaos.model.login;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author G.G
 * @date 2020/2/14 16:34
 */
@Data
@RequiredArgsConstructor(staticName = "of")
public class AppLoginDto extends LoginDto {
    @NotBlank(message = "账户不能为空")
    @NonNull
    private String username;
    @NotBlank(message = "密码不能为空")
    @NonNull
    private String password;
}
