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
    TIME_OUT("408", "请求超时"),
    ACCESS_LIMIT("409", "访问限制"),
    USERNAME_PASSWORD_ERROR("411", "账号或密码错误"),
    NEED_AUTHORIZE("412", "需要授权"),
    //5xx 请求错误(业务)
    FAILURE("500", "执行失败"),
    VALIDATE("501", "参数错误"),
    UNKONW_ERROR("555", "未知错误");

    private String code;
    private String defaultMsg;

    ResultEnum(String code, String defaultMsg) {
        this.defaultMsg = defaultMsg;
        this.code = code;
    }


}
