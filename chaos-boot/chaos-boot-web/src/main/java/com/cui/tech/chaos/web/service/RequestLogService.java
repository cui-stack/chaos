package com.cui.tech.chaos.web.service;

import com.cui.tech.chaos.web.base.BaseController;
import com.cui.tech.chaos.web.service.rest.ChaosLogRestService;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author G.G
 * @date 2020/5/28 16:41
 */
@Component
@Slf4j
public class RequestLogService extends BaseController {

    @Autowired
    private ChaosLogRestService chaosLogRestService;

    @Value("${app.manage.host:}")
    private String host;

    public void logRqquest(HttpServletRequest httpServletRequest, String ip, String uri, long time, String request, String response) {
        if (uri.startsWith("/manage/chaos_log/add")) {
            return;
        }
        String rq = request.substring(0, request.length() > 250 ? 250 : request.length());
        String rp = response.substring(0, response.length() > 250 ? 250 : response.length());
        String userMu = "";
        if (uri.startsWith("/api") || uri.startsWith("/wxmini")) {
            userMu = getWxLoginMU(httpServletRequest);
        } else if (uri.startsWith("/manage")) {
            if (!StringUtils.isEmpty(host)) {
                //获取managetoken,在manage服务器解析userMu
                userMu = getToken(httpServletRequest);
            } else {
                try {
                    userMu = getMnLoginMU(httpServletRequest);
                } catch (SignatureException e) {
                    log.warn("失效token[{}]", getToken(httpServletRequest));
                }
            }
        }

        chaosLogRestService.log(userMu, ip, uri, time, rq, rp);

    }
}
