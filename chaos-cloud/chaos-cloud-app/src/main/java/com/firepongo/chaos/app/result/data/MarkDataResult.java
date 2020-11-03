package com.firepongo.chaos.app.result.data;

import io.swagger.annotations.ApiModel;
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
public class MarkDataResult<T> extends DataResult {
    private String mark;

}
