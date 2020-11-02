package com.firepongo.chaos.manage.api.data;

import com.firepongo.chaos.app.db.DATA;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="ChaosRoleData", description="")
public class ChaosRoleData extends DATA{

    private String name;

    private String info;

    private String indexLink;

    private String platformMu;

}
