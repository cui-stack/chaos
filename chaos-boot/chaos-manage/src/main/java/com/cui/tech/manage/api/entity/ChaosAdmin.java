package com.cui.tech.manage.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cui.tech.chaos.model.db.MuModel;
import com.cui.tech.chaos.model.db.MuSortModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

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
@TableName("chaos_admin")
@ApiModel(value = "ChaosAdmin实体", description = "")
public class ChaosAdmin extends MuSortModel {

    @TableField("username")
    private String username;

    @TableField(value = "password", select = false)
    private String password;

    @TableField("name")
    private String name;

    @TableField("phone")
    private String phone;

    @TableField("email")
    private String email;

    @TableField("qq")
    private String qq;

    @TableField("zip")
    private String zip;

    @TableField("addr")
    private String addr;

    @TableField("lastlogin_time")
    private LocalDateTime lastloginTime;

    @TableField("login_times")
    private Integer loginTimes;

    @TableField("ip")
    private String ip;

    /**
     * 0正常 1被封
     */
    @ApiModelProperty(value = "0正常 1被封")
    @TableField("status")
    private Integer status;

    @TableField("platform_mu")
    private String platformMu;

}
