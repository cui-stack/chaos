package com.cui.tech.chaos.manage.api.entity;

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
@TableName("chaos_admin_role")
@ApiModel(value="ChaosAdminRole实体", description="")
public class ChaosAdminRole extends MuModel {

    @TableField("admin_mu")
    private String adminMu;

    @TableField("role_mu")
    private String roleMu;

}
