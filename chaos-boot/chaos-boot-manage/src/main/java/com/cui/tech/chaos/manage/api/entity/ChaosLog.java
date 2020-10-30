package com.cui.tech.chaos.manage.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cui.tech.chaos.model.db.MuModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author G.G
 * @since 2020-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Accessors(chain = true)
@TableName("chaos_log")
@ApiModel(value="ChaosLog实体", description="")
public class ChaosLog extends MuModel {

    @TableField("user_mu")
    private String userMu;

    @TableField("ip")
    private String ip;

    @TableField("uri")
    private String uri;

    @TableField("time")
    private Long time;

    @TableField("request")
    private String request;

    @TableField("response")
    private String response;


}
