package com.firepongo.chaos.admin.api.data;

import com.firepongo.chaos.app.db.DATA;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@ApiModel(value = "ChaosAdminRoleData", description = "")
public class ChaosAdminRoleData extends DATA {
    @NonNull
    private String adminMu;

    private String roleMu;

}
