package com.firepongo.chaos.app.login.wxmini;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author G.G
 * @date 2020/2/16 15:20
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class WxBindPhoneDto  {
    private String wid;
    private String iv;
    private String encryptedData;
    private String session_key;
}
