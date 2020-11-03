package com.firepongo.chaos.app.exception;

import com.firepongo.chaos.app.result.Result;
import com.firepongo.chaos.app.result.ResultEnum;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    private Result result;

    public BusinessException(ResultEnum resultEnum) {
        super(resultEnum.getDefaultMsg());
        this.result = new Result(resultEnum.getCode(), resultEnum.getDefaultMsg());
    }

    public BusinessException(String code, String msg) {
        super(msg);
        this.result = new Result(code, msg);
    }

}
