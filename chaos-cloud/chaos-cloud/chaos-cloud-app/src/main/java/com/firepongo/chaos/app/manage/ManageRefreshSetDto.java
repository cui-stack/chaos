package com.firepongo.chaos.app.manage;

import com.firepongo.chaos.app.login.ManageLoginUser;
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
