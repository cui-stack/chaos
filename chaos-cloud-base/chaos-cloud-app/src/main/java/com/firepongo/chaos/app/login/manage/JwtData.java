package com.firepongo.chaos.app.login.manage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author G.G
 * @date 2020/2/23 15:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtData {
    String userMu;
    Date exp;
}
