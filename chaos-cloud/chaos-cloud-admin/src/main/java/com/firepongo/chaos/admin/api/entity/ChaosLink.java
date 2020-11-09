package com.firepongo.chaos.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.firepongo.chaos.app.db.MuSortModel;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author G.G
 * @since 2020-08-17
 */
@Data
@Accessors(chain = true)
@TableName("chaos_link")
@ApiModel(value="ChaosLink实体", description="")
public class ChaosLink extends MuSortModel {

    @TableField("link")
    private String link;

    @TableField("title")
    private String title;

    @TableField("info")
    private String info;

    @TableField("hits")
    private Integer hits;

    @TableField("status")
    private Integer status;


}
