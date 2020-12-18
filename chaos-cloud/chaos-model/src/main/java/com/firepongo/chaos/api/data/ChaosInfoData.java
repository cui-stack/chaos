package com.firepongo.chaos.api.data;

import com.firepongo.chaos.app.db.DATA;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel(value="ChaosInfoData", description="")
public class ChaosInfoData extends DATA{

    private String title;

    private String info;

    private String imgUrl;


}
