package com.firepongo.chaos.app.login;

import com.firepongo.chaos.app.db.MU;
import lombok.Data;

import java.util.Date;

/**
 * @author G.G
 * 登录用户(在线)
 */
@Data
public class LoginUser extends MU {
    private String ip;
    private Date loginTime;
}
