package com.firepongo.chaos.app.db;

import com.firepongo.chaos.app.DTO;
import lombok.*;
import lombok.experimental.Accessors;


/**
 * @author G.G
 * @date 2019/11/7 17:30
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class MU extends DTO {
    @NonNull
    private String mu;

}
