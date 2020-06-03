package com.cui.tech.chaos.lite.exception;

import com.cui.tech.chaos.model.result.Result;
import com.cui.tech.chaos.model.result.ResultEnum;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;

/**
 * @author G.G
 * @date 2020/5/28 16:27
 */
@Data
public class AuthenticationException extends RuntimeException {
    //出错的对象类
    private String ObjectName = "AuthenticationInterceptor";
    //出错的对象方法
    private String methodName = "preHandle";
    private Result result;
    private HttpServletRequest request;

    public AuthenticationException(ResultEnum resultEnum) {
        super(resultEnum.getDefaultMsg());
        this.result = new Result(resultEnum.getCode(), resultEnum.getDefaultMsg());
    }

    public AuthenticationException(String code, String msg) {
        super(msg);
        this.result = new Result(code, msg);
    }

    public AuthenticationException(ResultEnum resultEnum, String msg, HttpServletRequest request) {
        super(msg);
        this.result = new Result(resultEnum.getCode(), msg);
        this.request = request;
    }

    public AuthenticationException(ResultEnum resultEnum, String msg) {
        super(msg);
        this.result = new Result(resultEnum.getCode(), msg);
    }

    public AuthenticationException(String ObjectName, String methodName, ResultEnum resultEnum) {
        super(resultEnum.getDefaultMsg());
        this.ObjectName = ObjectName;
        this.methodName = methodName;
        this.result = new Result(resultEnum.getCode(), resultEnum.getDefaultMsg());
    }

    public AuthenticationException(String ObjectName, String methodName, ResultEnum resultEnum, String msg) {
        super(msg);
        this.ObjectName = ObjectName;
        this.methodName = methodName;
        this.result = new Result(resultEnum.getCode(), msg);
    }
}
