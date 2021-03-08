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
 * @since 2020-04-28
 */
@Data
@Accessors(chain = true)
@TableName("chaos_log")
@ApiModel(value = "ChaosLog实体", description = "")
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

    @TableField("platform")
    private String platform;

    @TableField("env")
    private String env;

}
