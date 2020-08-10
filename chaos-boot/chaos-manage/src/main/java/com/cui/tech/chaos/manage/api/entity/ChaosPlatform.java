package com.cui.tech.chaos.manage.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cui.tech.chaos.model.db.MuModel;
import com.cui.tech.chaos.model.db.MuSortModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
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


}
