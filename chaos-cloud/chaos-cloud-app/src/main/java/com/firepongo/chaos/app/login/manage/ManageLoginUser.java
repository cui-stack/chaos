package com.firepongo.chaos.app.login.manage;

import com.firepongo.chaos.app.login.LoginUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ManageLoginUser extends LoginUser {
    private String token;
    private String username;
    private String phone;
    private String role;
    private String indexLink;

    public ManageLoginUser(String mu, String username, String phone) {
        this.setMu(mu);
        this.username = username;
        this.phone = phone;
    }

}
