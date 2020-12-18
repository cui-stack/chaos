package com.firepongo.chaos.api.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.firepongo.chaos.app.db.DATA;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel(value="ChaosUserData", description="")
public class ChaosUserData extends DATA{

    private String openid;

    private String unionid;

    private String name;

    private String email;

    private String phone;

    private String qq;

    private String zip;

    private String addr;

    private String nick;

    private String head;

    private String sex;

    private String country;

    private String prov;

    private String city;

    private String dist;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private LocalDateTime lastloginTime;

    private String ip;

    private Integer userType;

    private String referrer;

    private String source;

    private String intention;

    private String wxcountry;

    private String wxprov;

    private String wxcity;


}
