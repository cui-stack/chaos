package com.firepongo.chaos.app.address;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author G.G
 * @date 2020/3/11 13:49
 */
@Data
@NoArgsConstructor
public class BaiduLocation {
    private String address;
    private Integer status;
    private String content_address;
    private String content_address_detail_city;
    private Integer content_address_detail_city_code;
    private String content_address_detail_district;
    private String content_address_detail_province;
    private String point_x;
    private String point_y;

}
