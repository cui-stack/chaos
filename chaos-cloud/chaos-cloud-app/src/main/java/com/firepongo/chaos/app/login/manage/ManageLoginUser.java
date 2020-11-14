package com.firepongo.chaos.app.login.manage;

import com.firepongo.chaos.app.login.LoginUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ManageLoginUser extends LoginUser {
    private String token;
    private String username;
    private String phone;
    private String roleInfo;
    private String roleName;
    private String indexLink;
    private Integer status;
    List<ManageMenu> menus;

    public ManageLoginUser(String mu, String username, String phone, Integer status) {
        this.setMu(mu);
        this.username = username;
        this.phone = phone;
        this.status = status;
    }

}
