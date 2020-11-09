package com.firepongo.chaos.app.login.wxmini;

import com.firepongo.chaos.app.login.LoginUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class WxMiniLoginUser extends LoginUser {
    private String token;
    private String nick;
    private String name;
    private String phone;

    public WxMiniLoginUser(String mu, String token) {
        this.setMu(mu);
        this.token = token;
    }
}
