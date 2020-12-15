package com.firepongo.chaos.app.login;

import com.firepongo.chaos.app.DTO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author G.G
 * 登录请求用户
 * ip
 * 计划存储  请求头数据
 */
@Data
@NoArgsConstructor
public class LoginDto extends DTO {
    private String ip;

}
