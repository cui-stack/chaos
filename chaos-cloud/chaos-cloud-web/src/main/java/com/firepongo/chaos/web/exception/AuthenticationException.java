package com.firepongo.chaos.web.exception;

import com.firepongo.chaos.app.exception.BusinessException;
import com.firepongo.chaos.app.result.Result;
import com.firepongo.chaos.app.result.ResultEnum;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;

/**
 * @author G.G
 * @date 2020/5/28 16:27
 */
@Data
public class AuthenticationException extends BusinessException {
    private Result result;
    private HttpServletRequest request;

    public AuthenticationException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public AuthenticationException(String code, String msg) {
        super(code,msg);
    }

    public AuthenticationException(String code, String msg, HttpServletRequest request) {
        super(code,msg);
        this.request = request;
    }


}
