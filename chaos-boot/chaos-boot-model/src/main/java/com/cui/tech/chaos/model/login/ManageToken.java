package com.cui.tech.chaos.model.login;

import com.cui.tech.chaos.model.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author G.G
 * @date 2020/8/19 10:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManageToken extends DTO {
    private String token;
}
