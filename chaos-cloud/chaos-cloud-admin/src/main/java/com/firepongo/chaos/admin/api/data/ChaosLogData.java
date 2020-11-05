package com.firepongo.chaos.admin.api.data;

import com.firepongo.chaos.app.db.DATA;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

@Data
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

}
