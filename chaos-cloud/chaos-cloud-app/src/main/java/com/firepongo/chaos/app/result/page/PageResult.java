package com.firepongo.chaos.app.result.page;

import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.result.Result;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author G>G
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "列表结果对象", description = "")
public class PageResult<T> extends Result {
    private PageList<T> page;


}
