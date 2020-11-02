package com.firepongo.chaos.app.manage;

import com.firepongo.chaos.app.login.ManageToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author G.G
 * @date 2020/8/6 17:39
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ChaosAdminGetDto extends ManageToken {
    private String userMuToken;

    public ChaosAdminGetDto(String token, String userMuToken) {
        super(token);
        this.userMuToken = userMuToken;
    }

}
