package com.firepongo.chaos.admin.api.data;

import com.firepongo.chaos.app.db.DATA;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@ApiModel(value="ChaosPlatformData", description="")
public class ChaosPlatformData extends DATA{

    /**
     * 平台名称
     */
    @ApiModelProperty(value = "平台名称")
    private String name;

    private String info;

    private LocalDateTime createTime;


}
