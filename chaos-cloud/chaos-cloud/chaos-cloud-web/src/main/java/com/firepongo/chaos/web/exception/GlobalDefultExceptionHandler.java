package com.firepongo.chaos.web.exception;

import com.firepongo.chaos.app.exception.BusinessException;
import com.firepongo.chaos.web.service.RequestLogService;
import com.firepongo.chaos.app.result.Result;
import com.firepongo.chaos.web.util.addr.IpUtil;
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
        log.warn(e.getMessage());
        Result result = e.getResult();
        return result;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public Result authenticationException(AuthenticationException e) {
        log.info(e.getMessage());
        Result result = e.getResult();
        requestLogService.logRqquest(e.getRequest(), IpUtil.getIpAddr(e.getRequest()), e.getRequest().getRequestURI(), -1, "token:" + e.getRequest().getHeader("token"), result.getMsg());
        return result;
    }

}

