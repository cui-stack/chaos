package com.firepongo.chaos.app.login.wxmini;

import com.firepongo.chaos.app.login.LoginDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author G.G
 * @date 2020/2/14 16:34
 */
@Data
@NoArgsConstructor
public class WxMiniLoginDto extends LoginDto {
    private String code;

    private String openid;
    private String unionid;
    private String token;
    private String referrer;
    private String source;

}
