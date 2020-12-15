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
 * @since 2020-11-17
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("chaos_user")
@ApiModel(value="ChaosUser实体", description="")
public class ChaosUser extends MuModel {

    @TableField("openid")
    private String openid;

    @TableField("unionid")
    private String unionid;

    @TableField("name")
    private String name;

    @TableField("email")
    private String email;

    @TableField("phone")
    private String phone;

    @TableField("qq")
    private String qq;

    @TableField("zip")
    private String zip;

    @TableField("addr")
    private String addr;

    @TableField("nick")
    private String nick;

    @TableField("head")
    private String head;

    @TableField("sex")
    private String sex;

    @TableField("country")
    private String country;

    @TableField("prov")
    private String prov;

    @TableField("city")
    private String city;

    @TableField("dist")
    private String dist;

    @TableField("lastlogin_time")
    private LocalDateTime lastloginTime;

    @TableField("ip")
    private String ip;

    @TableField("userType")
    private Integer userType;

    @TableField("referrer")
    private String referrer;

    @TableField("source")
    private String source;

    @TableField("intention")
    private String intention;

    @TableField("wxcountry")
    private String wxcountry;

    @TableField("wxprov")
    private String wxprov;

    @TableField("wxcity")
    private String wxcity;


}
