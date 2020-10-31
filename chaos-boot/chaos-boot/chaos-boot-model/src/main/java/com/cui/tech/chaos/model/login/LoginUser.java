package com.cui.tech.chaos.model.login;

import com.cui.tech.chaos.model.db.MU;
import lombok.Data;

import java.util.Date;

@Data
public class LoginUser extends MU {
    private String ip;
    private Date loginTime;

    public LoginUser() {
    }

    private LoginUser(String mu) {
        super(mu);
    }
}
