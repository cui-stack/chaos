package com.firepongo.chaos.app.login.wxmini;

import com.firepongo.chaos.app.login.LoginUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WxMiniLoginUser extends LoginUser {
    private String token;
    private String nick;
    private String username;
    private String phone;

}
