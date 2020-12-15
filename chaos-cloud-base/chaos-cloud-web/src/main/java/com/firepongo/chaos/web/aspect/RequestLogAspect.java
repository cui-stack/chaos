package com.firepongo.chaos.web.aspect;

import com.firepongo.chaos.web.service.RequestLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author G.G
 * @date 2020/3/12 21:57
 */

@Component
@Aspect
@Slf4j
public class RequestLogAspect {
    @Autowired
    private RequestLogService requestLogService;

    /**
     * 定义切点
     */
    @Pointcut("execution(* com..controller..*(..))")
    public void requestServer() {
    }

    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        return requestLogService.doLog(pjp);
    }


}
