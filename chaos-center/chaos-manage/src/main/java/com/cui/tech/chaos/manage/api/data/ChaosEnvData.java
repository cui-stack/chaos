package com.cui.tech.chaos.manage.api.data;

import com.cui.tech.chaos.model.db.DATA;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
//@RequiredArgsConstructor(staticName = "of")
@ApiModel(value = "ChaosEnvData", description = "")
public class ChaosEnvData extends DATA {

    private String mu;

    private String link;

    private String title;

    private String info;

    private LocalDateTime createTime;


}
