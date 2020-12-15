package com.firepongo.chaos.app.page;

import com.firepongo.chaos.app.constant.Constants;
import com.firepongo.chaos.app.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
@ApiModel(value = "分页对象", description = "")
public class PageQueryDto<T> extends DTO {
    @ApiModelProperty(value = "第几页", example = "1")
    private Integer pageNum = Constants.DEFAULT_PAGE;
    @ApiModelProperty(value = "总条数", example = "10")
    private Integer pageSize = Constants.DEFAULT_PAGE_SIZE;
    @ApiModelProperty(value = "结果对象")
    @NonNull
    private T data;

}
