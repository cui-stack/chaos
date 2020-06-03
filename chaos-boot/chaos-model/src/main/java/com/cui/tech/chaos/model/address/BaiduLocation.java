package com.cui.tech.chaos.model.address;

import lombok.Data;

/**
 * @author G.G
 * @date 2020/3/11 13:49
 */
@Data
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
