package com.firepongo.chaos.manage.api.data;

import com.firepongo.chaos.app.db.DATA;
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
@ApiModel(value = "ChaosEnvData", description = "")
public class ChaosEnvData extends DATA {

    private String link;

    private String title;

    private String info;

    private LocalDateTime createTime;


}
