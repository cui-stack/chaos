package com.cui.tech.chaos.model.redis;

import com.cui.tech.chaos.model.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author G.G
 * @date 2020/8/6 17:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisSetDto extends DTO {
    String key;
    Object value;
    Long time;
}
