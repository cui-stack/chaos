package com.firepongo.chaos.manage.api.data;

import com.firepongo.chaos.app.db.DATA;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
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

    @NonNull
    private String ip;

    /**
     * 0正常 1被封
     */
    @ApiModelProperty(value = "0正常 1被封")
    private Integer status;

    private String roleMu;

    private String platformMu;

    private String oldPassword;

}
