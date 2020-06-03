package com.cui.tech.manage.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cui.tech.chaos.model.db.MuModel;
import io.swagger.annotations.ApiModel;
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
 * @since 2020-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Accessors(chain = true)
@TableName("chaos_permission")
@ApiModel(value="ChaosPermission实体", description="")
public class ChaosPermission extends MuModel {

    @TableField("isroot")
    private Integer isroot;

    @TableField("supmu")
    private String supmu;

    @TableField("link")
    private String link;

    @TableField("title")
    private String title;

    @TableField("platform_mu")
    private String platformMu;

    @TableField("icon")
    private String icon;

}
