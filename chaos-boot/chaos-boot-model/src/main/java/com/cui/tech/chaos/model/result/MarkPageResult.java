package com.cui.tech.chaos.model.result;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "列表结果对象", description = "")
public class MarkPageResult<T> extends PageResult {

    private String mark;

    public MarkPageResult() {
        super();
    }

    @Override
    public MarkPageResult msg(String msg) {
        this.setMsg(msg);
        return this;
    }
}
