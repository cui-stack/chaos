package com.firepongo.chaos.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.firepongo.chaos.app.db.MuModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author G.G
 * @since 2020-12-18
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("chaos_info")
@ApiModel(value="ChaosInfo实体", description="")
public class ChaosInfo extends MuModel {

    @TableField("title")
    private String title;

    @TableField("info")
    private String info;

    @TableField("img_url")
    private String imgUrl;


}
