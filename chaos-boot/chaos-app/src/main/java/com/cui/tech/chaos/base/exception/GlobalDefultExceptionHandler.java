package com.cui.tech.chaos.base.exception;

import com.cui.tech.chaos.service.RequestLogService;
import com.cui.tech.chaos.model.result.Result;
import com.cui.tech.chaos.util.addr.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalDefultExceptionHandler {
    @Autowired
    private RequestLogService requestLogService;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result defultExcepitonHandler(Exception e) {
        log.error("系统未知错误");
        e.printStackTrace();
        Result result = new Result();
        result.unknow();
        return result;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result businessException(BusinessException e) {
        Result result = e.getResult();
        log.warn(e.getObjectName() + "|" + e.getMethodName() + "=>" + e.getMessage());
        e.printStackTrace();
        return result;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public Result authenticationException(AuthenticationException e) {
        Result result = e.getResult();
        log.warn(e.getObjectName() + "|" + e.getMethodName() + "=>" + e.getMessage());
        e.printStackTrace();
        requestLogService.logRqquest(e.getRequest(), IpUtil.getIpAddr(e.getRequest()), e.getRequest().getRequestURI(), -1, "token:" + e.getRequest().getHeader("token"), result.getMsg());
        return result;
    }

}

