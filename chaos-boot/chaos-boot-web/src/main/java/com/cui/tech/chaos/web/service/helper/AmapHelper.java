package com.cui.tech.chaos.web.service.helper;

import com.alibaba.fastjson.JSONObject;
import com.cui.tech.chaos.model.address.GaoDeAddress;
import com.cui.tech.chaos.model.address.Geo;
import com.cui.tech.chaos.web.util.addr.GeoHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 高德地图
 * @author G.G
 * @date 2020/2/15 15:52
 */
@Component
public class AmapHelper {
    @Value("${app.amap.key:}")
    String key;
    @Autowired
    private RestTemplate restTemplate;

    public Geo geo(String city, String address) {
        String url = "https://restapi.amap.com/v3/geocode/geo?city=" + city + "&address=" + address + "&key="
                + key;
        JSONObject resp = restTemplate.getForObject(url, JSONObject.class);
        String status = (String) resp.get("status");
        Geo g = new Geo();
        if (status.equals("1")) {
            ArrayList geocodes = (ArrayList) resp.get("geocodes");
            LinkedHashMap geocode = (LinkedHashMap) geocodes.get(0);
            String location = (String) geocode.get("location");
            String[] ls = location.split(",");
            g.setLng(ls[0]);
            g.setLat(ls[1]);
            g.setGeo(new GeoHashUtil().encode(Double.parseDouble(g.getLat()), Double.parseDouble(g.getLng())));
        }
        return g;
    }

    public GaoDeAddress regeo(String lng, String lat) {
        String url = "http://restapi.amap.com/v3/geocode/regeo?location=" + lng + "," + lat + "&key=" + key + "&radius=200&extensions=all";
        JSONObject resp = restTemplate.getForObject(url, JSONObject.class);
        LinkedHashMap map = (LinkedHashMap) resp.get("regeocode");
        LinkedHashMap am = (LinkedHashMap) map.get("addressComponent");
        GaoDeAddress gaoDeAddress = new GaoDeAddress();
        gaoDeAddress.setCity((String) am.get("city"));
        gaoDeAddress.setProv((String) am.get("province"));
        gaoDeAddress.setDist((String) am.get("district"));
        gaoDeAddress.setAddr((String) map.get("formatted_address"));
        return gaoDeAddress;
    }

}

