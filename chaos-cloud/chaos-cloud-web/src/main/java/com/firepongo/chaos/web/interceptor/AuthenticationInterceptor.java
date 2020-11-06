package com.firepongo.chaos.web.interceptor;

import com.firepongo.chaos.app.constant.RoleConstant;
import com.firepongo.chaos.app.login.LoginUser;
import com.firepongo.chaos.app.login.manage.JwtData;
import com.firepongo.chaos.app.login.manage.ManageLoginUser;
import com.firepongo.chaos.app.login.wxmini.WxMiniLoginUser;
import com.firepongo.chaos.app.result.ResultEnum;
import com.firepongo.chaos.web.annotation.AccessLimit;
import com.firepongo.chaos.web.annotation.AccessTotalLimit;
import com.firepongo.chaos.web.annotation.ManageLoginToken;
import com.firepongo.chaos.web.annotation.WxminiLoginToken;
import com.firepongo.chaos.web.exception.AuthenticationException;
import com.firepongo.chaos.web.service.AccessLimitService;
import com.firepongo.chaos.web.service.JwtService;
import com.firepongo.chaos.web.service.RedisService;
import com.firepongo.chaos.web.service.login.ILoginService;
import com.firepongo.chaos.web.service.login.manage.ManageLoginKeyService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author G.G
 */
@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Qualifier("mnLoginService")
    @Autowired(required = false)
    ILoginService mnLoginService;
    @Qualifier("wxLoginService")
    @Autowired(required = false)
    ILoginService wxLoginService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private AccessLimitService accessLimitService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        LoginUser loginUser = null;
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(ManageLoginToken.class)) {
            ManageLoginToken manageLoginToken = method.getAnnotation(ManageLoginToken.class);
            if (manageLoginToken.required()) {
                if (mnLoginService == null) {
                    return false;
                }
                String token = httpServletRequest.getHeader("token");
                loginUser = handleManageLogin(token, httpServletRequest);
                List<String> result = Arrays.asList(manageLoginToken.roles());
                ArrayList<String> roles = new ArrayList<>(result);
                if (!roles.contains(RoleConstant.GUEST)) {
                    String role = ((ManageLoginUser) loginUser).getRole();
                    if (!roles.contains(role)) {
                        return false;
                    }
                }
            }
        } else if (method.isAnnotationPresent(WxminiLoginToken.class)) {
            WxminiLoginToken wxminiLoginToken = method.getAnnotation(WxminiLoginToken.class);
            if (wxminiLoginToken.required()) {
                if (wxLoginService == null) {
                    return false;
                }
                String token = httpServletRequest.getHeader("token");
                loginUser = handleWxminiLogin(token, httpServletRequest);
            }
        }

        if (!StringUtils.isEmpty(loginUser)) {
            if (method.isAnnotationPresent(AccessLimit.class)) {
                AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
                accessLimitService.handleAccessLimit(loginUser.getMu(), accessLimit.seconds(), accessLimit.maxCount(), httpServletRequest);
            }
            if (method.isAnnotationPresent(AccessTotalLimit.class)) {
                AccessTotalLimit accessTotalLimit = method.getAnnotation(AccessTotalLimit.class);
                accessLimitService.handleAccessTotalLimit(loginUser.getMu(), accessTotalLimit.seconds(), accessTotalLimit.maxCount(), httpServletRequest);
            }
        }
        return true;
    }

    private LoginUser handleWxminiLogin(String token, HttpServletRequest httpServletRequest) {
        // 执行认证
        if (StringUtils.isEmpty(token)) {
            throw new AuthenticationException(ResultEnum.LOGIN_AGAIN.getCode(), "无效token，请重新登录", httpServletRequest);
        }
        WxMiniLoginUser loginUser = (WxMiniLoginUser) wxLoginService.getLoginUser(token);
        if (StringUtils.isEmpty(loginUser)) {
            throw new AuthenticationException(ResultEnum.LOGIN_AGAIN.getCode(), "用户不存在，请重新登录", httpServletRequest);
        }
        return loginUser;
    }

    private ManageLoginUser handleManageLogin(String token, HttpServletRequest httpServletRequest) {
        // 执行认证
        if (StringUtils.isEmpty(token)) {
            throw new AuthenticationException(ResultEnum.LOGIN_AGAIN.getCode(), "无效token，请重新登录", httpServletRequest);
        }
        JwtData jwtData = null;
        try {
            jwtData = jwtService.getJwtData(token);
        } catch (ExpiredJwtException e) {
            String userMu = (String) e.getClaims().get("userMu");
            String refresh = mnLoginService.refreshToken(userMu);
            if (StringUtils.isEmpty(refresh)) {
                throw new AuthenticationException(ResultEnum.LOGIN_AGAIN.getCode(), "续签失效，请重新登录", httpServletRequest);
            }
            throw new AuthenticationException(ResultEnum.REFRESH_TOKEN.getCode(), refresh, httpServletRequest);
        }
        if (jwtData.getUserMu() == null) {
            throw new AuthenticationException(ResultEnum.LOGIN_AGAIN.getCode(), "无效token，请重新登录", httpServletRequest);
        }
        ManageLoginUser loginUser = null;
        try {
            loginUser = (ManageLoginUser) mnLoginService.getLoginUser(jwtData.getUserMu());
        } catch (Exception e) {
            throw new AuthenticationException(ResultEnum.LOGIN_AGAIN.getCode(), "无效token，请重新登录", httpServletRequest);
        }
        log.info("后台用户登录,用户token:[{}],session:[{}]", token, loginUser);
        if (StringUtils.isEmpty(loginUser)) {
            throw new AuthenticationException(ResultEnum.LOGIN_AGAIN.getCode(), "用户不存在，请重新登录", httpServletRequest);
        }
        if (!token.equals(loginUser.getToken())) {
            throw new AuthenticationException(ResultEnum.LOGIN_AGAIN.getCode(), "服务器token更新，请重新登录", httpServletRequest);
        }
        return loginUser;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
