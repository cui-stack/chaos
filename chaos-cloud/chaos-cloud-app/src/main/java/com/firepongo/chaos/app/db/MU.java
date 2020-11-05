package com.firepongo.chaos.app.db;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author G.G
 * @date 2019/11/7 17:30
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class MU {
    @NonNull
    private String mu;

}
