package com.firepongo.chaos.app.login;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.firepongo.chaos.app.db.MU;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author G.G
 * 登录用户(在线)
 */
@Data
@NoArgsConstructor
public class LoginUser extends MU {
    private String ip;
    private String loginTime;

    public LoginUser(String mu, String ip, String loginTime) {
        this.setMu(mu);
        this.ip = ip;
        this.loginTime = loginTime;
    }
}
