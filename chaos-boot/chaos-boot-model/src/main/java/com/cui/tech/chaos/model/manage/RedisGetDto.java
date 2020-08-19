package com.cui.tech.chaos.model.manage;

import com.cui.tech.chaos.model.DTO;
import com.cui.tech.chaos.model.login.ManageToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author G.G
 * @date 2020/8/6 17:39
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class RedisGetDto extends ManageToken {
    String key;

    public RedisGetDto(String token, String key) {
        super(token);
        this.key = key;
    }
}
