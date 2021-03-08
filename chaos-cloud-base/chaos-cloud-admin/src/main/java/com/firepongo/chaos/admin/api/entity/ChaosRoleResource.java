package com.firepongo.chaos.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.firepongo.chaos.app.db.MuModel;
import io.swagger.annotations.ApiModel;
import lombok.*;
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
@TableName("chaos_role_resource")
@ApiModel(value = "ChaosRoleResource实体", description = "")
public class ChaosRoleResource extends MuModel {
    @TableField("role_mu")
    private String roleMu;
    @TableField("resource_mu")
    private String resourceMu;

}
