package com.firepongo.chaos.app.login.manage;

import com.firepongo.chaos.app.login.LoginUser;
import lombok.Data;

@Data
public class ManageLoginUser extends LoginUser {
    private String token;
    private String username;
    private String phone;

    public ManageLoginUser() {
    }

    public ManageLoginUser(Integer id, String mu, String username, String phone) {
        this.setMu(mu);
        this.username = username;
        this.phone = phone;
    }

}
