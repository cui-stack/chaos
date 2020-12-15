package com.firepongo.chaos.app.result.data;

import com.firepongo.chaos.app.result.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author G.G
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "数据结果", description = "")
public class DataResult<T> extends Result {

    /**
     * 响应数据
     */
    @ApiModelProperty(value = "结果对象")
    private T data;


}
