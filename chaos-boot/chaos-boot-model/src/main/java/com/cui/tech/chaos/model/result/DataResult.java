package com.cui.tech.chaos.model.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author G.G
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "数据结果", description = "")
public class DataResult<T> extends Result {

    /**
     * 响应数据
     */
    @ApiModelProperty(value = "结果对象")
    private T data;

    public DataResult() {
    }


}
