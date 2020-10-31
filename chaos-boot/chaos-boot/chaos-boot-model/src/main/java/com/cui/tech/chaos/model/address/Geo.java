package com.cui.tech.chaos.model.address;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author G.G
 * @date 2020/2/15 15:55
 */
@Data
@RequiredArgsConstructor(staticName = "of")
public class Geo {
    @NonNull
    private String lon;
    @NonNull
    private String lat;
    private String geo;
}
