package com.firepongo.chaos.web.service.rest;

import com.firepongo.chaos.app.login.ManageLoginUser;
import com.firepongo.chaos.app.manage.RedisGetDto;
import com.firepongo.chaos.app.manage.RedisSetDto;
import com.firepongo.chaos.app.result.DataResult;
import com.firepongo.chaos.app.role.ManageLoginUserRole;
import com.firepongo.chaos.web.service.helper.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

/**
 * @author G.G
 * @date 2020/8/6 11:04
 */
@Component
public class ManageDataRestService {
    @Autowired
    protected RedisHelper redisHelper;
    @Value("${app.manage.host:}")
    private String host;
    @Value("${app.manage.token:}")
    private String token;

    @Autowired
    private RestTemplate restTemplate;

    public boolean set(String key, Object value, long time) {
        if (StringUtils.isEmpty(host)) {
            return redisHelper.set(key, value, time);
        }
        return false;
    }

    public boolean refreshToken(String key, Object value, long time) {
        if (StringUtils.isEmpty(host)) {
            return redisHelper.set(key, value, time);
        } else {
            ResponseEntity<DataResult> responseEntity =
                    restTemplate.postForEntity(host + "/manage/data/refreshToken", new RedisSetDto(token, key, value, time), DataResult.class);
            return (Boolean) responseEntity.getBody().getData();
        }
    }

    public Object get(String key) {
        if (StringUtils.isEmpty(host)) {
            return redisHelper.get(key);
        } else {
            ResponseEntity<DataResult> responseEntity =
                    restTemplate.postForEntity(host + "/manage/data/get", new RedisGetDto(token, key), DataResult.class);
            LinkedHashMap map = (LinkedHashMap) responseEntity.getBody().getData();
            LinkedHashMap roleMap = (LinkedHashMap) map.get("role");
            ManageLoginUserRole role = new ManageLoginUserRole((String) roleMap.get("name"), (String) roleMap.get("info"), (String) roleMap.get("indexLink"));
            ManageLoginUser user = new ManageLoginUser((Integer) map.get("id"), (String) map.get("mu"), (String) map.get("username"), (String) map.get("phone"), role);
            user.setToken((String) map.get("token"));
            return user;
        }

    }


}
