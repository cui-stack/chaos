package com.firepongo.chaos.app.login.app;

import com.firepongo.chaos.app.login.LoginDto;
import lombok.Data;

/**
 * @author G.G
 * @date 2020/2/14 16:34
 */
@Data
public class AppPhoneLoginDto extends LoginDto {
    private String phone;
    private String code;
}
