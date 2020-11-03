package com.firepongo.chaos.app.result;

import com.firepongo.chaos.app.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "基础结果对象", description = "")
public class Result extends DTO {

    @ApiModelProperty(value = "响应编码", example = "200")
    private String code;

    @ApiModelProperty(value = "响应消息", example = "ok")
    private String msg;

    public void failure() {
        this.setCode(ResultEnum.FAILURE.getCode());
        this.setMsg(ResultEnum.FAILURE.getDefaultMsg());
    }

    public void unknow() {
        this.setCode(ResultEnum.UNKONW_ERROR.getCode());
        this.setMsg(ResultEnum.UNKONW_ERROR.getDefaultMsg());
    }

    public void success() {
        this.setCode(ResultEnum.SUCCESS.getCode());
        this.setMsg(ResultEnum.SUCCESS.getDefaultMsg());
    }

    public void msg(String code ,String msg) {
        this.setCode(code);
        this.setMsg(msg);
    }

}
