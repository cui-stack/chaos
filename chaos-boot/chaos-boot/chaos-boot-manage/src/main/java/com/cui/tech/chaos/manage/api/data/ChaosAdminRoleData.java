package com.cui.tech.chaos.manage.api.data;

import com.cui.tech.chaos.model.db.DATA;
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
