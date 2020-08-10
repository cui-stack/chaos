package com.cui.tech.chaos.model.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author G.G
 */
@Data
@ApiModel(value = "数据结果", description = "")
public class MarkDataResult<T> extends DataResult {
    private String mark;

    public MarkDataResult() {
        super();
    }


}
