package com.cui.tech.chaos.model.result;

import com.cui.tech.chaos.model.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author G.G
 * @date 2020/5/28 18:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LimitDto extends DTO {
    private String mu;
    private String uri;
    private Integer count;
}
