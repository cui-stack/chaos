package com.cui.tech.chaos.model.manage;

import com.cui.tech.chaos.model.login.ManageLoginUser;
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
public class ManageRefreshSetDto extends ManageToken {
    String key;
    ManageLoginUser value;
    Long time;

    public ManageRefreshSetDto(String token, String key, ManageLoginUser value, Long time) {
        super(token);
        this.key = key;
        this.value = value;
        this.time = time;
    }

}
