package com.firepongo.chaos.admin.api.data;

import com.firepongo.chaos.app.db.DATA;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@ApiModel(value = "ChaosResourceData", description = "")
public class ChaosResourceData extends DATA {

    private Integer isRoot;

    private String supMu;

    private String link;

    private String title;

    private String supTitle;

    private String platformMu;

    private String icon;

    private LocalDateTime createTime;
}
