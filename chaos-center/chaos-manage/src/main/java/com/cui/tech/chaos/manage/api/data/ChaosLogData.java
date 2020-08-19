package com.cui.tech.chaos.manage.api.data;

import com.cui.tech.chaos.model.db.DATA;
import com.cui.tech.chaos.model.login.ManageToken;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "ChaosLogData", description = "")
public class ChaosLogData extends DATA {

    private String userMu;

    private String ip;

    private String uri;

    private Long time;

    private String request;

    private String response;

}
