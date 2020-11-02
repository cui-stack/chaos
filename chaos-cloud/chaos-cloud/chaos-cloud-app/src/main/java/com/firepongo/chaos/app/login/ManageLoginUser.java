package com.firepongo.chaos.app.login;

import com.firepongo.chaos.app.role.ManageLoginUserRole;
import lombok.Data;

@Data
public class ManageLoginUser extends LoginUser {
    private String token;
    private String username;
    private String phone;
    private ManageLoginUserRole role;

    public ManageLoginUser() {
    }

    public ManageLoginUser(Integer id, String mu, String username, String phone) {
        this.setId(id);
        this.setMu(mu);
        this.username = username;
        this.phone = phone;
        this.role = new ManageLoginUserRole();
    }

    public ManageLoginUser(Integer id, String mu, String username, String phone, ManageLoginUserRole role) {
        this.setId(id);
        this.setMu(mu);
        this.username = username;
        this.phone = phone;
        this.role = role;
    }
}
