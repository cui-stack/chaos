package com.cui.tech.chaos.model.manage;

import com.cui.tech.chaos.model.login.ManageToken;
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
