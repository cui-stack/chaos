package com.firepongo.chaos.app.login;

import com.firepongo.chaos.app.db.MU;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author G.G
 * 登录用户(在线)
 */
@Data
@NoArgsConstructor
public class LoginUser extends MU {
    private String ip;
    private Date loginTime;

    public LoginUser(String mu, String ip, Date loginTime) {
        this.setMu(mu);
        this.ip = ip;
        this.loginTime = loginTime;
    }
}
