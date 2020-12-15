package com.firepongo.chaos.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.firepongo.chaos.app.db.MuSortModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author G.G
 * @since 2020-04-23
 */
@Data
@Accessors(chain = true)
@TableName("chaos_platform")
@ApiModel(value="ChaosPlatform实体", description="")
public class ChaosPlatform extends MuSortModel {

    /**
    * 平台名称
    */
    @ApiModelProperty(value = "平台名称")
    @TableField("name")
    private String name;


    @ApiModelProperty(value = "平台名称")
    @TableField("info")
    private String info;

}
