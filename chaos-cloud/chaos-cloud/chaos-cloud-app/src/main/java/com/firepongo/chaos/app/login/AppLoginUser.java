package com.firepongo.chaos.app.login;

import lombok.Data;

@Data
public class AppLoginUser extends LoginUser {
    private String token;
    private String username;
    private String phone;

    public AppLoginUser() {
    }

    public AppLoginUser(Integer id, String mu, String username, String phone) {
        this.setId(id);
        this.setMu(mu);
        this.username = username;
        this.phone = phone;
    }
}
