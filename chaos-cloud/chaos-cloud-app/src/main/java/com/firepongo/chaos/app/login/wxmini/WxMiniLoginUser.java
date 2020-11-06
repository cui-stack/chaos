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
    private String username;
    private String phone;

    public WxMiniLoginUser(String mu, String token, String nick, String username, String phone) {
        this.setMu(mu);
        this.token = token;
        this.nick = nick;
        this.username = username;
        this.phone = phone;
    }
}
