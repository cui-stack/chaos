package com.cui.tech.manage.api.data;

import com.cui.tech.chaos.model.db.DATA;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ChaosLogData", description = "")
public class ChaosLogData extends DATA {

    private String userMu;

    private String ip;

    private String uri;

    private Long time;

    private String request;

    private String response;


}
