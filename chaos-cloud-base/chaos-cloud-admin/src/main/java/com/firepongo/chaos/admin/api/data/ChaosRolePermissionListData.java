package com.firepongo.chaos.admin.api.data;

import com.firepongo.chaos.app.DTO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel(value = "ChaosRolePermissionListData", description = "")
public class ChaosRolePermissionListData extends DTO {

    private String roleMu;

    private List<String> addMus;

    private List<String> deleteMus;

}
