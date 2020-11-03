package com.firepongo.chaos.web.exception;

import com.firepongo.chaos.app.result.Result;
import com.firepongo.chaos.app.result.ResultEnum;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;

/**
 * @author G.G
 * @date 2020/5/28 16:27
 */
@Data
public class AuthenticationException extends RuntimeException {
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

}
