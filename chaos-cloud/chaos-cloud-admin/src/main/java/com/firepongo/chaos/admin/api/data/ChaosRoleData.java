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
@ApiModel(value="ChaosRoleData", description="")
public class ChaosRoleData extends DATA{

    private String name;

    private String info;

    private String indexLink;

    private String platformMu;

}
