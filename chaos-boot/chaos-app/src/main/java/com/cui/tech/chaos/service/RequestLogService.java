package com.cui.tech.chaos.service;

import com.cui.tech.chaos.base.BaseController;
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

    public void logRqquest(HttpServletRequest httpServletRequest, String remoteAddress, String requestURI, long time, String request, String response) {
        if (iLogService != null) {
            String userMu = "";
            if (requestURI.startsWith("/api") || requestURI.startsWith("/wxmini")) {
                userMu = getWxLoginMU(httpServletRequest);
            } else if (requestURI.startsWith("/manage")) {
                try {
                    userMu = getMnLoginMU(httpServletRequest);
                } catch (SignatureException e) {
                    log.warn("失效token[{}]", getToken(httpServletRequest));
                }
            }
            iLogService.log(userMu, remoteAddress, requestURI, time, request.substring(0, request.length() > 250 ? 250 : request.length()), response.substring(0, response.length() > 250 ? 250 : response.length()));
        }
    }
}
