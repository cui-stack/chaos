package com.cui.tech.chaos.web.service.rest;

import com.cui.tech.chaos.model.manage.ChaosLogAddDto;
import com.cui.tech.chaos.model.result.DataResult;
import com.cui.tech.chaos.web.base.BaseController;
import com.cui.tech.chaos.web.service.ILogService;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
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
