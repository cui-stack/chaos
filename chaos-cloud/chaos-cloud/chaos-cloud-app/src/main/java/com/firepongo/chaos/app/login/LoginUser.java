package com.firepongo.chaos.app.login;

import com.firepongo.chaos.app.db.MU;
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
