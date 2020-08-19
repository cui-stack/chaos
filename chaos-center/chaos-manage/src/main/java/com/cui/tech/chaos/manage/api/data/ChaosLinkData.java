package com.cui.tech.chaos.manage.api.data;

import com.cui.tech.chaos.model.db.DATA;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
//@RequiredArgsConstructor(staticName = "of")
@ApiModel(value = "ChaosLinkData", description = "")
public class ChaosLinkData extends DATA {

    private String mu;

    private String link;

    private String title;

    private String info;

    private Integer hits;

    private Long sort;

    private LocalDateTime createTime;

    private Integer status;

}
