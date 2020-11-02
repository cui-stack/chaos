package com.firepongo.chaos.manage.api.data;

import com.firepongo.chaos.app.db.DATA;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ChaosRolePermissionData", description = "")
public class ChaosRolePermissionData extends DATA {

    private String roleMu;

    private String permissionMu;

    private String permissionTitle;

}
