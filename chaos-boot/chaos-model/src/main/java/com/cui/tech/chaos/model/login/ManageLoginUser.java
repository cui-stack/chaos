package com.cui.tech.chaos.model.login;

import com.cui.tech.chaos.model.role.ManageLoginUserRole;
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
}
