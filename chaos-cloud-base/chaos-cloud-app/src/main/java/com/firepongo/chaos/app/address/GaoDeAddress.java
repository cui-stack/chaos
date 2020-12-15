package com.firepongo.chaos.app.address;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author G.G
 * @date 2020/2/15 16:50
 */
@Data
@NoArgsConstructor
public class GaoDeAddress {
    private String city;
    private String prov;
    private String dist;
    private String addr;
}
