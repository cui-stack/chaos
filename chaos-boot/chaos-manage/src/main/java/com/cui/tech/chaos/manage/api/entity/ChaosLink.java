package com.cui.tech.chaos.manage.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cui.tech.chaos.model.db.MuSortModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author G.G
 * @since 2020-08-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
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
