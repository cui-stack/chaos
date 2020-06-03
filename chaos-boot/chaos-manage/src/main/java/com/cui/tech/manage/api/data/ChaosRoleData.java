package com.cui.tech.manage.api.data;

import com.cui.tech.chaos.model.db.DATA;
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
