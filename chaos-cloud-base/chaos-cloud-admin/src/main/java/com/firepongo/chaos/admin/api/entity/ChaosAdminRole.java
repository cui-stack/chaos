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
@TableName("chaos_admin_role")
@ApiModel(value="ChaosAdminRole实体", description="")
public class ChaosAdminRole extends MuModel {

    @TableField("admin_mu")
    private String adminMu;

    @TableField("role_mu")
    private String roleMu;

}
