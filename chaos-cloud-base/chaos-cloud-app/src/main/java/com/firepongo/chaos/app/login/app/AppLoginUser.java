package com.firepongo.chaos.app.login.app;

import com.firepongo.chaos.app.login.LoginUser;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppLoginUser extends LoginUser {
    private String token;
    private String username;
    private String phone;


    public AppLoginUser(String mu, String token, String username, String phone) {
        this.setMu(mu);
        this.token = token;
        this.username = username;
        this.phone = phone;
    }
}
