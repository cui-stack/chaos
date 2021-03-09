package com.firepongo.chaos.app.admin;

import com.firepongo.chaos.app.db.DATA;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@ApiModel(value = "ChaosAdminData", description = "")
public class ChaosAdminData extends DATA {
    private String username;

    private String password;

    private String name;

    private String phone;

    private String email;

    private String qq;

    private String zip;

    private String addr;

    private LocalDateTime lastloginTime;

    private Integer loginTimes;

    private String ip;

    /**
     * 0正常 1被封
     */
    @ApiModelProperty(value = "0正常 1被封")
    private Integer status;

    private String roleMu;

    private String platformMu;

    private String oldPassword;

    private LocalDateTime createTime;

}
