package com.cui.tech.chaos.model.result;

import com.cui.tech.chaos.model.DTO;
import com.cui.tech.chaos.model.login.LoginUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "基础结果对象", description = "")
public class Result extends DTO {
    /**
     * 响应编码
     */
    @ApiModelProperty(value = "响应编码", example = "200")
    private String code;

    /**
     * 响应消息
     */
    @ApiModelProperty(value = "响应消息", example = "ok")
    private String msg;

    public Result() {
    }

    public Result(String code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
    }

    public Result failure() {
        this.setCode(ResultEnum.FAILURE.getCode());
        this.setMsg(ResultEnum.FAILURE.getDefaultMsg());
        return this;
    }

    public Result unknow() {
        this.setCode(ResultEnum.UNKONW_ERROR.getCode());
        this.setMsg(ResultEnum.UNKONW_ERROR.getDefaultMsg());
        return this;
    }

    public Result success() {
        this.setCode(ResultEnum.SUCCESS.getCode());
        this.setMsg(ResultEnum.SUCCESS.getDefaultMsg());
        return this;
    }

}
