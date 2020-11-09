package com.firepongo.chaos.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.firepongo.chaos.app.db.MuModel;
import io.swagger.annotations.ApiModel;
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
 * @since 2020-04-14
 */
@Data
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
