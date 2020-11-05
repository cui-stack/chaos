package com.firepongo.chaos.admin.api.data;

import com.firepongo.chaos.app.db.DATA;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
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
