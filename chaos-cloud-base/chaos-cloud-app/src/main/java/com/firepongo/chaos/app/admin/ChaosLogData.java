package com.firepongo.chaos.app.admin;

import com.firepongo.chaos.app.db.DATA;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@Accessors(chain = true)
@ApiModel(value = "ChaosLogData", description = "")
public class ChaosLogData extends DATA {
    @NonNull
    private String userMu;
    @NonNull
    private String ip;
    @NonNull
    private String uri;
    @NonNull
    private Long time;
    @NonNull
    private String request;
    @NonNull
    private String response;
    @NonNull
    private String platform;
    @NonNull
    private String env;
    private LocalDateTime createTime;

}
