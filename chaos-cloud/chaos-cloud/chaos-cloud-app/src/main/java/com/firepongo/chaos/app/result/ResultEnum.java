package com.firepongo.chaos.app.result;

import lombok.Getter;

@Getter
public enum ResultEnum {
    //2XX 请求成功
    SUCCESS("200", "执行成功"),
    REFRESH_TOKEN("201", "续签成功"),
    //4xx 资源权限异常
    LOGIN_AGAIN("401", "登录过期"),
    TOKEN_EXP("403", "Token失效"),
    ACCESS_LIMIT("409", "访问限制"),
    //5xx 请求错误(业务)
    FAILURE("500", "执行失败"),
    VALIDATE("501", "参数错误"),
    UNKONW_ERROR("505", "未知错误");

    private String code;
    private String defaultMsg;

    ResultEnum(String code, String defaultMsg) {
        this.defaultMsg = defaultMsg;
        this.code = code;
    }


}
