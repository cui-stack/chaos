package com.firepongo.chaos.manage.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.firepongo.chaos.app.db.MuModel;
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
@TableName("chaos_role")
@ApiModel(value="ChaosRole实体", description="")
public class ChaosRole extends MuModel {

    @TableField("name")
    private String name;

    @TableField("info")
    private String info;

    @TableField("index_link")
    private String indexLink;

    @TableField("platform_mu")
    private String platformMu;

}
