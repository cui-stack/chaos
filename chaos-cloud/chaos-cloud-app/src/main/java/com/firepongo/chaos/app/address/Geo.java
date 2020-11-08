package com.firepongo.chaos.app.address;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author G.G
 * @date 2020/2/15 15:55
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Geo {
    @NonNull
    private String lon;
    @NonNull
    private String lat;
    private String geo;
}
