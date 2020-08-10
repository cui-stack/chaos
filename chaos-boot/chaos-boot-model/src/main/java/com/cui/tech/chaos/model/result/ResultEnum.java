package com.cui.tech.chaos.model.result;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS("200", "执行成功"),
    REFRESH_TOKEN("201", "续签成功"),
    FAILURE("500", "执行失败"),
    VALIDATE("501", "参数错误"),
    UNKONW_ERROR("505", "未知错误"),
    LOGIN_AGAIN("401", "登录过期"),
    TOKEN_EXP("403", "Token过期"),
    ACCESS_LIMIT("509", "访问限制");
    private String code;
    private String defaultMsg;

    ResultEnum(String code, String defaultMsg) {
        this.defaultMsg = defaultMsg;
        this.code = code;
    }


}
