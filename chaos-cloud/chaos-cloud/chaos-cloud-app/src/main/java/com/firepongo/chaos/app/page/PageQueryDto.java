package com.firepongo.chaos.app.page;

import com.firepongo.chaos.app.Constants;
import com.firepongo.chaos.app.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页对象", description = "")
public class PageQueryDto<T> extends DTO {
    @ApiModelProperty(value = "第几页", example = "1")
    private Integer pageNum = Constants.DEFAULT_PAGE;
    @ApiModelProperty(value = "总条数", example = "10")
    private Integer pageSize = Constants.DEFAULT_PAGE_SIZE;
    @ApiModelProperty(value = "结果对象")
    private T data;

    public PageQueryDto() {
    }

    public PageQueryDto(T data) {
        this.data = data;
    }

    public PageQueryDto(Integer pageNum, Integer pageSize, T data) {
        this.data = data;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
