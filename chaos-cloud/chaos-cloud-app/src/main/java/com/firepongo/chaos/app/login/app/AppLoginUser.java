package com.firepongo.chaos.app.login.app;

import com.firepongo.chaos.app.login.LoginUser;
import lombok.Data;

@Data
public class AppLoginUser extends LoginUser {
    private String token;
    private String username;
    private String phone;

    public AppLoginUser() {
    }

    public AppLoginUser(Integer id, String mu, String username, String phone) {
        this.setMu(mu);
        this.username = username;
        this.phone = phone;
    }
}
