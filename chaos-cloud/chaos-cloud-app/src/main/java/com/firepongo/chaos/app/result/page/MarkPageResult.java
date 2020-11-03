package com.firepongo.chaos.app.result.page;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "列表结果对象", description = "")
public class MarkPageResult<T> extends PageResult {

    private String mark;


}
