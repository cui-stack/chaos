package com.cui.tech.chaos.web.service;

import com.cui.tech.chaos.web.base.BaseController;
import com.cui.tech.chaos.web.service.rest.ChaosLogRestService;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author G.G
 * @date 2020/5/28 16:41
 */
@Component
@Slf4j
public class RequestLogService extends BaseController {
    @Autowired(required = false)
    private ILogService iLogService;
    @Autowired
    private ChaosLogRestService chaosLogRestService;

    public void logRqquest(HttpServletRequest httpServletRequest, String ip, String uri, long time, String request, String response) {
        String rq = request.substring(0, request.length() > 250 ? 250 : request.length());
        String rp = response.substring(0, response.length() > 250 ? 250 : response.length());
        if (iLogService != null) {
            String userMu = "";
            if (uri.startsWith("/api") || uri.startsWith("/wxmini")) {
                userMu = getWxLoginMU(httpServletRequest);
            } else if (uri.startsWith("/manage")) {
                try {
                    userMu = getMnLoginMU(httpServletRequest);
                } catch (SignatureException e) {
                    log.warn("失效token[{}]", getToken(httpServletRequest));
                }
            }
            iLogService.log(userMu, ip, uri, time, rq, rp);
        } else {
            chaosLogRestService.log(getToken(httpServletRequest), ip, uri, time, rq, rp);
        }
    }
}
