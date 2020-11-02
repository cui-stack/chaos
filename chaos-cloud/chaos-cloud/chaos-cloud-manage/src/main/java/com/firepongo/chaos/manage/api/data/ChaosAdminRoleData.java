package com.firepongo.chaos.manage.api.data;

import com.firepongo.chaos.app.db.DATA;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="ChaosAdminRoleData", description="")
public class ChaosAdminRoleData extends DATA{

    private String adminMu;

    private String roleMu;

}
