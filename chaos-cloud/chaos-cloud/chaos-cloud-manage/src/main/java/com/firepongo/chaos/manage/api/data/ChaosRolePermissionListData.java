package com.firepongo.chaos.manage.api.data;

import com.firepongo.chaos.app.DTO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ChaosRolePermissionListData", description = "")
public class ChaosRolePermissionListData extends DTO {

    private String roleMu;

    private List<String> addMus;

    private List<String> deleteMus;

}
