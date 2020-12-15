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
 * @since 2020-08-18
 */
@Data
@Accessors(chain = true)
@TableName("chaos_env")
@ApiModel(value="ChaosEnv实体", description="")
public class ChaosEnv extends MuModel {

    @TableField("link")
    private String link;

    @TableField("title")
    private String title;

    @TableField("info")
    private String info;

}
