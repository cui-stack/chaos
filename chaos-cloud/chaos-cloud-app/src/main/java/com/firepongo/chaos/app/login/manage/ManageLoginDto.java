package com.firepongo.chaos.app.login.manage;

import com.firepongo.chaos.app.login.LoginDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ManageLoginDto extends LoginDto {

    private String username;
    private String password;
    private String platformMu;

}
