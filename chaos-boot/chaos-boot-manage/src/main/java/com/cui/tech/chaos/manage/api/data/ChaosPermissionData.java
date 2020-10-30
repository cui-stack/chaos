package com.cui.tech.chaos.manage.api.data;

import com.cui.tech.chaos.model.db.DATA;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ChaosPermissionData", description = "")
public class ChaosPermissionData extends DATA {

    private Integer isroot;

    private String supmu;

    private String link;

    private String title;

    private String suptitle;

    private String platformMu;

    private String icon;


}
