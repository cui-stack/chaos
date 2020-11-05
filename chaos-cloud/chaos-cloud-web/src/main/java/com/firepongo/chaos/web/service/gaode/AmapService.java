package com.firepongo.chaos.web.service.gaode;

import com.alibaba.fastjson.JSONObject;
import com.firepongo.chaos.app.address.GaoDeAddress;
import com.firepongo.chaos.app.address.Geo;
import com.firepongo.chaos.web.helper.GeoHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 高德地图
 *
 * @author G.G
 * @date 2020/2/15 15:52
 */
@Component
public class AmapService {
    @Value("${app.amap.key:}")
    String key;
    @Autowired
    private RestTemplate restTemplate;

    public Geo geo(String city, String address) {
        String url = "https://restapi.amap.com/v3/geocode/geo?city=" + city + "&address=" + address + "&key="
                + key;
        JSONObject resp = restTemplate.getForObject(url, JSONObject.class);
        String status = (String) resp.get("status");
        Geo g = null;
        if (status.equals("1")) {
            ArrayList geocodes = (ArrayList) resp.get("geocodes");
            LinkedHashMap geocode = (LinkedHashMap) geocodes.get(0);
            String location = (String) geocode.get("location");
            String[] ls = location.split(",");
            g = Geo.of(ls[0], ls[1]);
            g.setGeo(new GeoHashUtil().encode(Double.parseDouble(g.getLat()), Double.parseDouble(g.getLon())));
        }
        return g;
    }

    public GaoDeAddress regeo(String lng, String lat) {
        String url = "http://restapi.amap.com/v3/geocode/regeo?location=" + lng + "," + lat + "&key=" + key + "&radius=200&extensions=all";
        JSONObject resp = restTemplate.getForObject(url, JSONObject.class);
        LinkedHashMap map = (LinkedHashMap) resp.get("regeocode");
        LinkedHashMap am = (LinkedHashMap) map.get("addressComponent");
        GaoDeAddress gaoDeAddress = new GaoDeAddress();

        Object city = am.get("city");
        gaoDeAddress.setCity(city instanceof String ? (String) city : "");

        Object province = am.get("province");
        gaoDeAddress.setProv(province instanceof String ? (String) province : "");

        Object district = am.get("district");
        gaoDeAddress.setDist(district instanceof String ? (String) district : "");

        Object formatted_address = map.get("formatted_address");
        gaoDeAddress.setAddr(formatted_address instanceof String ? (String) formatted_address : "");
        return gaoDeAddress;
    }

}

