package com.firepongo.chaos.app.result;

import com.firepongo.chaos.app.page.PageList;
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

    }

}
