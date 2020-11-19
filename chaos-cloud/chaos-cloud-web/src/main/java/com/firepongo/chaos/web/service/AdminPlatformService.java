package com.firepongo.chaos.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.firepongo.chaos.app.exception.BusinessException;
import com.firepongo.chaos.app.login.manage.ManageLoginUser;
import com.firepongo.chaos.app.result.ResultEnum;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.web.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author G.G
 * @date 2020/11/13 17:33
 */
@Component
public class AdminPlatformService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${app.admin.host:localhost:38089}")
    private String host;
    @Autowired
    private RedisService redisService;
    @Value("${spring.profiles.active:}")
    private String env;
    @Value("${spring.application.name:}")
    private String platform;
    @Value("${app.admin.isLog:false}")
    private String isLog;
    @Value("${app.admin.username:}")
    private String username;
    @Value("${app.admin.password:}")
    private String password;
    @Autowired(required = false)
    private ILogService iLogService;

    public DataResult requestAdmin(String path, String json) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("token", (String) redisService.get("app_admin_token"));
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        requestHeaders.setContentType(type);
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> requestEntity = new HttpEntity<>(json, requestHeaders);
        String url = host + path;
        ResponseEntity<DataResult> responseEntity = restTemplate.postForEntity(url, requestEntity, DataResult.class);
        DataResult result = responseEntity.getBody();
        if (result.getCode().equals(ResultEnum.REFRESH_TOKEN.getCode())) {
            redisService.set("app_admin_token", result.getMsg(), 7 * 24 * 60 * 60);
            this.requestAdmin(path, json);
        } else if (result.getCode().equals(ResultEnum.LOGIN_AGAIN.getCode())) {
            this.platformLogin();
        }
        return result;
    }

    public void platformLogin() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.createObjectNode();
        ((ObjectNode) rootNode).put("username", username);
        ((ObjectNode) rootNode).put("password", password);
        ((ObjectNode) rootNode).put("platformMu", 1);
        String json = getJson(mapper, rootNode);
        DataResult result = requestAdmin("/manage/login", json);
        if (result.getCode().equals(ResultEnum.SUCCESS.getCode())) {
            ManageLoginUser user = mapper.convertValue(result.getData(), new TypeReference<ManageLoginUser>() {
            });
            redisService.set("app_admin_token", user.getToken(), 7 * 24 * 60 * 60);
        } else {
            throw new BusinessException("500", "服务调用登录失败!");
        }
    }

    public ManageLoginUser userLogin(String username, String password, String platformMu) throws AuthenticationException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.createObjectNode();
        ((ObjectNode) rootNode).put("username", username);
        ((ObjectNode) rootNode).put("password", password);
        ((ObjectNode) rootNode).put("platformMu", platformMu);
        String json = getJson(mapper, rootNode);
        DataResult<ManageLoginUser> result = requestAdmin("/manage/chaos_admin/selectByUp", json);
        if (result.getCode().equals(ResultEnum.SUCCESS.getCode())) {
            ManageLoginUser user = mapper.convertValue(result.getData(), new TypeReference<ManageLoginUser>() {
            });
            return user;
        } else {
            return null;
        }
    }

    public ManageLoginUser doPhoneLogin(String phone, String platformMu) throws AuthenticationException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.createObjectNode();
        ((ObjectNode) rootNode).put("phone", phone);
        ((ObjectNode) rootNode).put("platformMu", platformMu);
        String json = getJson(mapper, rootNode);
        DataResult<ManageLoginUser> result = requestAdmin("/manage/chaos_admin/doPhoneLogin", json);
        if (result.getCode().equals(ResultEnum.SUCCESS.getCode())) {
            ManageLoginUser user = mapper.convertValue(result.getData(), new TypeReference<ManageLoginUser>() {
            });
            return user;
        } else {
            return null;
        }
    }

    public void log(String userMu, String ip, String uri, long time, String request, String response) {
        if (iLogService != null) {
            iLogService.log(userMu, ip, uri, time, request, response, platform, env);
        } else {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.createObjectNode();
            ((ObjectNode) rootNode).put("userMu", userMu);
            ((ObjectNode) rootNode).put("ip", ip);
            ((ObjectNode) rootNode).put("uri", uri);
            ((ObjectNode) rootNode).put("time", time);
            ((ObjectNode) rootNode).put("request", request);
            ((ObjectNode) rootNode).put("response", response);
            ((ObjectNode) rootNode).put("platform", platform);
            ((ObjectNode) rootNode).put("env", env);
            String json = getJson(mapper, rootNode);
            DataResult result = requestAdmin("/manage/chaos_log/add", json);
        }
    }

    public String adminName(String mu) {
        String adminName = (String) redisService.hget("app_admin_name", "mu");
        if (StringUtils.isEmpty(adminName)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.createObjectNode();
            ((ObjectNode) rootNode).put("mu", mu);
            String json = getJson(mapper, rootNode);
            DataResult<LinkedHashMap<String, String>> result = requestAdmin("/manage/chaos_admin/one", json);
            adminName = result.getData().get("name");
            redisService.hset("app_admin_name", "mu", adminName);
        }
        return adminName;

    }

    private String getJson(ObjectMapper mapper, JsonNode rootNode) {
        String json = "";
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        } catch (JsonProcessingException je) {
            throw new BusinessException("500", "服务调用登录失败!");
        }
        return json;
    }
}
