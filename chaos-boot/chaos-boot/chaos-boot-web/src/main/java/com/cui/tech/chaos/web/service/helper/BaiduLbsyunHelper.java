package com.cui.tech.chaos.web.service.helper;

import com.alibaba.fastjson.JSONObject;
import com.cui.tech.chaos.web.util.addr.BaiduLbsyunSnCal;
import com.cui.tech.chaos.model.address.BaiduLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author G.G
 * @date 2020/3/11 11:36
 */
@Component
public class BaiduLbsyunHelper {
    @Value("${app.baidu.lbsyun.ak:}")
    String ak;
    @Value("${app.baidu.lbsyun.sk:}")
    String sk;

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) throws UnsupportedEncodingException {
        BaiduLbsyunHelper u = new BaiduLbsyunHelper();
        u.location("119.139.197.158");

    }

    public BaiduLocation location(String ip) throws UnsupportedEncodingException {
        String url = "https://api.map.baidu.com/location/ip?ak=" + ak + "&ip=" + ip + "&coor=bd09ll" + "&sn=" + sn(ip);
        JSONObject resp = restTemplate.getForObject(url, JSONObject.class);
        BaiduLocation location = new BaiduLocation();
        location.setAddress((String) resp.get("address"));
        location.setStatus((Integer) resp.get("status"));

        LinkedHashMap content = (LinkedHashMap) resp.get("content");
        location.setContent_address((String) content.get("address"));

        LinkedHashMap address_detail = (LinkedHashMap) content.get("address_detail");
        location.setContent_address_detail_city((String) address_detail.get("city"));
        location.setContent_address_detail_city_code((Integer) address_detail.get("city_code"));
        location.setContent_address_detail_district((String) address_detail.get("district"));
        location.setContent_address_detail_province((String) address_detail.get("province"));

        LinkedHashMap point = (LinkedHashMap) content.get("point");
        location.setPoint_x((String) point.get("x"));
        location.setPoint_y((String) point.get("y"));
        return location;
    }

    private String sn(String ip) throws UnsupportedEncodingException {
        BaiduLbsyunSnCal snCal = new BaiduLbsyunSnCal();

        // 计算sn跟参数对出现顺序有关，get请求请使用LinkedHashMap保存<key,value>，该方法根据key的插入顺序排序；post请使用TreeMap保存<key,value>，该方法会自动将key按照字母a-z顺序排序。所以get请求可自定义参数顺序（sn参数必须在最后）发送请求，但是post请求必须按照字母a-z顺序填充body（sn参数必须在最后）。以get请求为例：http://api.map.baidu.com/geocoder/v2/?address=百度大厦&output=json&ak=yourak，paramsMap中先放入address，再放output，然后放ak，放入顺序必须跟get请求中对应参数的出现顺序保持一致。

        Map paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("ak", ak);
        paramsMap.put("ip", ip);
        paramsMap.put("coor", "bd09ll");

        // 调用下面的toQueryString方法，对LinkedHashMap内所有value作utf8编码，拼接返回结果address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourak
        String paramsStr = snCal.toQueryString(paramsMap);

        // 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接yoursk得到/geocoder/v2/?address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourakyoursk
        String wholeStr = new String("/location/ip?" + paramsStr + sk);

        // 对上面wholeStr再作utf8编码
        String tempStr = URLEncoder.encode(wholeStr, "UTF-8");

        // 调用下面的MD5方法得到最后的sn签名7de5a22212ffaa9e326444c75a58f9a0
        String sn = snCal.MD5(tempStr);
        System.out.println(sn);
        return sn;
    }
}
