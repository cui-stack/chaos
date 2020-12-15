package com.firepongo.chaos.web.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.login.wxmini.WxMiniLoginUser;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.web.base.BaseController;
import com.firepongo.chaos.web.helper.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
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
 * @date 2020/5/28 16:41
 */
@Component
@Slf4j
public class RequestLogService extends BaseController {

    @Autowired
    private AdminPlatformService adminPlatformService;

    public void logRequest(HttpServletRequest httpServletRequest, String ip, String uri, long time, String request, String response, Object result) {
        if (("/manage/chaos_log/add").equals(uri)) {
            return;
        }
        String userMu = "";
        if (uri.startsWith("/api") || uri.startsWith("/wxmini")) {
            try {
                userMu = getWxLoginUserByToken(getToken(httpServletRequest)).getMu();
            } catch (Exception e) {
            }
        } else if (uri.startsWith("/manage")) {
            try {
                userMu = getMnLoginUserByToken(getToken(httpServletRequest)).getMu();
            } catch (Exception e) {
            }
        }
        if (uri.equals("/wxmini/login")) {
            DataResult<WxMiniLoginUser> r = (DataResult<WxMiniLoginUser>) result;
            userMu = r.getData().getMu();
        }
        if (uri.equals("/manage/chaos_admin/add")) {
            DataResult<MU> data = (DataResult<MU>) result;
            if (data.getData() != null)
                userMu = data.getData().getMu();
        }
        String rq = request.substring(0, request.length() > 250 ? 250 : request.length());
        String rp = response.substring(0, response.length() > 250 ? 250 : response.length());
        adminPlatformService.log(userMu, ip, uri, time, rq, rp);

    }

    public Object doLog(ProceedingJoinPoint pjp) throws Throwable {
        long beginTime = System.currentTimeMillis();
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();

        String remoteAddress = IpUtil.getIpAddr(request);
        String requestURI = request.getRequestURI();
        String methodName = pjp.getSignature().getName();
        String clazzName = pjp.getTarget().getClass().getSimpleName();

        MethodSignature ms = (MethodSignature) pjp.getSignature();
        String[] parameterNames = ms.getParameterNames();
        Object[] parameterValues = pjp.getArgs();
        StringBuilder sb = new StringBuilder();
        if (parameterNames != null && parameterNames.length > 0) {
            for (int i = 0; i < parameterNames.length; i++) {
                if (parameterNames[i].equals("bindingResult") || parameterNames[i].equals("request")) {
                    break;
                }
                if ((parameterValues[i] instanceof HttpServletRequest) || (parameterValues[i] instanceof HttpServletResponse)) {
                    sb.append("[").append(parameterNames[i]).append("=").append(parameterValues[i]).append("]");
                } else {
                    sb.append("[").append(parameterNames[i]).append("=").append(JSON.toJSONString(parameterValues[i], SerializerFeature.WriteDateUseDateFormat)).append("]");
                }
            }
        }
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            log.error("切面处理请求错误！ IP信息->： 【{}】 URI信息->：【{}】 请求映射控制类->：【{}】 请求方法->：【{}】 请求参数列表->：【{}】",
                    remoteAddress, requestURI, clazzName, methodName, sb.toString());
            logRequest(request, remoteAddress, requestURI, 0, sb.toString(), "请求错误!", null);
            throw throwable;
        }
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
        log.info("请求操作成功！ 请求耗时：【{}】ms IP信息->： 【{}】  URI信息->：【{}】 请求映射控制类->：【{}】 请求方法->：【{}】 请求参数列表->：【{}】 返回值->：【{}】"
                , usedTime, remoteAddress, requestURI, clazzName, methodName, sb.toString(), resultJosnString);
        logRequest(request, remoteAddress, requestURI, usedTime, sb.toString(), resultJosnString, result);
        return result;
    }
}
