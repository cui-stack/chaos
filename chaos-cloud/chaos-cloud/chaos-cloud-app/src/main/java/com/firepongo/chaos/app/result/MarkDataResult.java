package com.firepongo.chaos.app.result;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author G.G
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "数据结果", description = "")
public class MarkDataResult<T> extends DataResult {
    private String mark;

    public MarkDataResult() {
    }

}
