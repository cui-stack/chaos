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
public class MuGetDto extends ManageToken {
    private String mu;

    public MuGetDto(String token, String mu) {
        super(token);
        this.mu = mu;
    }

}
