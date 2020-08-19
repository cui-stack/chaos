package com.cui.tech.chaos.web.service.rest;

import com.cui.tech.chaos.model.login.ManageLoginUser;
import com.cui.tech.chaos.model.manage.ChaosAdminGetDto;
import com.cui.tech.chaos.model.manage.MuGetDto;
import com.cui.tech.chaos.model.result.DataResult;
import com.cui.tech.chaos.model.role.ManageLoginUserRole;
import com.cui.tech.chaos.web.service.helper.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

/**
 * @author G.G
 * @date 2020/8/18 23:07
 */
@Component
public class ChaosAdminRestService {
    @Value("${app.manage.host:}")
    private String host;
    @Value("${app.manage.token:}")
    private String token;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    protected RedisHelper redisHelper;

    /**
     * 根据用户token 从Manage服务器获取登录用户信息
     *
     * @param data
     * @return
     */
    public LinkedHashMap get(ChaosAdminGetDto data) {
        if (StringUtils.isEmpty(host)) {
            return null;
        } else {
            data.setToken(token);
            ResponseEntity<DataResult> responseEntity =
                    restTemplate.postForEntity(host + "/manage/chaos_admin/get", data, DataResult.class);
            LinkedHashMap map = (LinkedHashMap) responseEntity.getBody().getData();
            return map;
        }

    }

    /**
     * 根据用户token 从Manage服务器获取登录用户信息,包括权限
     *
     * @param data
     * @return
     */
    public ManageLoginUser loginUser(ChaosAdminGetDto data) {
        if (StringUtils.isEmpty(host)) {
            return null;
        } else {
            data.setToken(token);
            ResponseEntity<DataResult> responseEntity =
                    restTemplate.postForEntity(host + "/manage/data/loginUser", data, DataResult.class);
            LinkedHashMap map = (LinkedHashMap) responseEntity.getBody().getData();
            LinkedHashMap roleMap = (LinkedHashMap) map.get("role");
            ManageLoginUserRole role = new ManageLoginUserRole((String) roleMap.get("name"), (String) roleMap.get("info"), (String) roleMap.get("indexLink"));
            ManageLoginUser user = new ManageLoginUser((Integer) map.get("id"), (String) map.get("mu"), (String) map.get("username"), (String) map.get("phone"), role);
            user.setToken((String) map.get("token"));
            return user;
        }
    }

    /**
     * 根据mu 从
     *
     * @param data
     * @return
     */
    public LinkedHashMap one_one(MuGetDto data) {
        if (StringUtils.isEmpty(host)) {
            return null;
        } else {
            data.setToken(token);
            ResponseEntity<DataResult> responseEntity =
                    restTemplate.postForEntity(host + "/manage/chaos_admin/one_one", data, DataResult.class);
            LinkedHashMap map = (LinkedHashMap) responseEntity.getBody().getData();
            return map;
        }

    }

    public String getAdminName(MuGetDto data) {
        String name = (String) redisHelper.get("chaos_admin_mu_" + data.getMu());
        if (StringUtils.isEmpty(name)) {
            LinkedHashMap map = one_one(data);
            name = (String) map.get("name");
            redisHelper.set("chaos_admin_mu_" + data.getMu(), name, 60 * 5);
        }
        return name;
    }

}
