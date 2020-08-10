package com.cui.tech.chaos.web.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cui.tech.chaos.web.service.RequestLogService;
import com.cui.tech.chaos.web.util.addr.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @Pointcut("execution(* com.cui.tech..controller.*.*(..))")
    public void requestServer() {
    }

    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        //记录请求开始执行时间：
        long beginTime = System.currentTimeMillis();
        //获取请求信息
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();

        //获取代理地址、请求地址、请求类名、方法名
        String remoteAddress = IpUtil.getIpAddr(request);
        String requestURI = request.getRequestURI();
        String methodName = pjp.getSignature().getName();
        String clazzName = pjp.getTarget().getClass().getSimpleName();

        //获取请求参数：
        MethodSignature ms = (MethodSignature) pjp.getSignature();
        //获取请求参数类型
        String[] parameterNames = ms.getParameterNames();
        //获取请求参数值
        Object[] parameterValues = pjp.getArgs();
        StringBuilder sb = new StringBuilder();
        //组合请求参数，进行日志打印
        if (parameterNames != null && parameterNames.length > 0) {
            for (int i = 0; i < parameterNames.length; i++) {
                if (parameterNames[i].equals("bindingResult")) {
                    break;
                }
                if (parameterNames[i].equals("request")) {
                    break;
                }
                if ((parameterValues[i] instanceof HttpServletRequest) || (parameterValues[i] instanceof HttpServletResponse)) {
                    sb.
                            append("[").
                            append(parameterNames[i]).append("=").append(parameterValues[i])
                            .append("]");
                } else {
                    sb.
                            append("[").
                            append(parameterNames[i]).append("=")
                            .append(JSON.toJSONString(parameterValues[i], SerializerFeature.WriteDateUseDateFormat))
                            .append("]");
                }
            }
        }
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            //请求操纵失败
            //记录错误日志
            log.error("切面处理请求错误！ IP信息->： 【{}】 " +
                            "URI信息->：【{}】 请求映射控制类->：【{}】 " +
                            "请求方法->：【{}】 请求参数列表->：【{}】", remoteAddress, requestURI, clazzName, methodName,
                    sb.toString());
            requestLogService.logRqquest(request, remoteAddress, requestURI, -1, sb.toString(), "");
            throw throwable;
        }
        //请求操作成功
        String resultJosnString = "";
        if (result != null) {
            if (result instanceof ResponseData) {
                resultJosnString = JSON.toJSONString(result, SerializerFeature.WriteDateUseDateFormat);
            } else {
                resultJosnString = String.valueOf(result);
            }
        }
        //记录请求完成执行时间：
        long endTime = System.currentTimeMillis();
        long usedTime = endTime - beginTime;
        //记录日志
        log.info("请求操作成功！ 请求耗时：【{}】ms " +
                        "IP信息->： 【{}】  URI信息->：【{}】 " +
                        "请求映射控制类->：【{}】 请求方法->：【{}】 " +
                        "请求参数列表->：【{}】 返回值->：【{}】", usedTime, remoteAddress, requestURI, clazzName,
                methodName, sb.toString(), resultJosnString);
        requestLogService.logRqquest(request, remoteAddress, requestURI, usedTime, sb.toString(), resultJosnString);
        return result;
    }

}
