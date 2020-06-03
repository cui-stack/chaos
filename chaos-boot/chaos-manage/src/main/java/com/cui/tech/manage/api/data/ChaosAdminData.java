package com.cui.tech.manage.api.data;

import com.cui.tech.chaos.model.db.DATA;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public ChaosAdminData(String ip) {
        this.ip = ip;
    }
}
