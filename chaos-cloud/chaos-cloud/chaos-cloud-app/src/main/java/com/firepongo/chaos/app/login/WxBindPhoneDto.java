package com.firepongo.chaos.app.login;

import lombok.Data;

/**
 * @author G.G
 * @date 2020/2/16 15:20
 */
@Data
public class WxBindPhoneDto  {
    private String wid;
    private String iv;
    private String encryptedData;
    private String session_key;
}
