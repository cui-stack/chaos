package com.cui.tech.chaos.model.result;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "多列表结果对象", description = "")
public class MarkPagesResult<T> extends PagesResult {

    private String mark;

    public MarkPagesResult() {
        super();
    }

}
