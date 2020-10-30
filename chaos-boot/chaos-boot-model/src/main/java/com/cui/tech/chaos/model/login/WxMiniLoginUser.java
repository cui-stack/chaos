package com.cui.tech.chaos.model.login;

import lombok.Data;

@Data
public class WxMiniLoginUser extends LoginUser {
    private String token;
    private String username;
    private String phone;

    public WxMiniLoginUser() {
    }

    public WxMiniLoginUser(Integer uid, String mu, String username, String phone) {
        this.setId(uid);
        this.setMu(mu);
        this.username = username;
        this.phone = phone;
    }

}
