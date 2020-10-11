package com.cui.tech.chaos.model.result;

import com.cui.tech.chaos.model.page.PageList;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;

@Data
@Accessors(chain = true)
@ApiModel(value = "多列表结果对象", description = "")
public class PagesResult<T> extends Result {

    private ArrayList<PageList<T>> pages;

    public PagesResult() {
        success();
    }

}
