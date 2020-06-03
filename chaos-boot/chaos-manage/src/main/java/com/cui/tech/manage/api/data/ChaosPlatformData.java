package com.cui.tech.manage.api.data;

import com.cui.tech.chaos.model.db.DATA;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="ChaosPlatformData", description="")
public class ChaosPlatformData extends DATA{

    /**
     * 平台名称
     */
    @ApiModelProperty(value = "平台名称")
    private String name;


}
