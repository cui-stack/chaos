package com.firepongo.chaos.app.login.manage;

import com.firepongo.chaos.app.DTO;
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
