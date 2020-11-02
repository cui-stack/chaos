package com.firepongo.chaos.app.result;

import com.firepongo.chaos.app.page.PageList;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author G>G
 */
@Data
@ApiModel(value = "列表结果对象", description = "")
public class PageResult<T> extends Result {
    private PageList<T> page;

    public PageResult() {
    }

}
