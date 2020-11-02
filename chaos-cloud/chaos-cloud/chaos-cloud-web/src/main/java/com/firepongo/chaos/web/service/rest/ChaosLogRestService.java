package com.firepongo.chaos.web.service.rest;

import com.firepongo.chaos.app.manage.ChaosLogAddDto;
import com.firepongo.chaos.app.result.DataResult;
import com.firepongo.chaos.web.service.ILogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

/**
 * @author G.G
 * @date 2020/5/28 16:41
 */
@Component
@Slf4j
public class ChaosLogRestService {
    @Value("${app.manage.host:}")
    private String host;
    @Value("${app.manage.token:}")
    private String token;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired(required = false)
    private ILogService iLogService;

    @Async
    public void log(String userMuToken, String ip, String uri, long time, String request, String response) {
        if (!StringUtils.isEmpty(host)) {
            ResponseEntity<DataResult> responseEntity =
                    restTemplate.postForEntity(host + "/manage/chaos_log/add", new ChaosLogAddDto(token, userMuToken, ip, uri, time, request.substring(0, request.length() > 250 ? 250 : request.length()), response.substring(0, response.length() > 250 ? 250 : response.length())), DataResult.class);
            LinkedHashMap map = (LinkedHashMap) responseEntity.getBody().getData();
        } else {
            if (iLogService != null) {
                iLogService.log(userMuToken, ip, uri, time, request, response);
            }
        }
    }
}
