package com.firepongo.chaos.web.service.login.manage;

import com.firepongo.chaos.app.login.LoginUser;
import com.firepongo.chaos.app.login.manage.ManageLoginUser;
import com.firepongo.chaos.app.result.ResultEnum;
import com.firepongo.chaos.web.exception.AuthenticationException;
import com.firepongo.chaos.web.service.admin.AdminPlatformService;
import com.firepongo.chaos.web.service.JwtService;
import com.firepongo.chaos.web.service.RedisService;
import com.firepongo.chaos.web.service.login.BaseLoginServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ManageLoginServiceImpl extends BaseLoginServiceImpl {

    @Autowired
    private ManageLoginKeyService loginKeyService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    protected RedisService redisService;
    @Autowired
    private AdminPlatformService adminPlatformService;

    @Override
    protected void dealRedisToken(LoginUser loginUser) {
        ManageLoginUser user = (ManageLoginUser) loginUser;
        String token = jwtService.createToken(user.getMu());
        user.setToken(token);
        redisService.set(loginKeyService.key(user.getMu()), user, 7 * 24 * 60 * 60);
    }

    @Override
    public String refreshToken(String userMu) {
        ManageLoginUser user;
        try {
            user = (ManageLoginUser) redisService.get(loginKeyService.key(userMu));
        } catch (Exception e) {
            throw new AuthenticationException(ResultEnum.LOGIN_AGAIN.getCode(), "无效token，请重新登录");
        }
        if (user == null) {
            return null;
        }
        String newToken = jwtService.createToken(user.getMu());
        user.setToken(newToken);
        redisService.set(loginKeyService.key(userMu), user, 7 * 24 * 60 * 60);
        return newToken;
    }

    @Override
    public LoginUser getLoginUser(String userMu) {
        if (StringUtils.isEmpty(userMu)) {
            return null;
        }
        try {
            return (LoginUser) redisService.get(loginKeyService.key(userMu));
        } catch (Exception e) {
            throw new AuthenticationException(ResultEnum.LOGIN_AGAIN.getCode(), "无效token，请重新登录");
        }
    }

    @Override
    public  void afterLogin(LoginUser loginUser){
        adminPlatformService.updateLoginLog(loginUser.getMu(),loginUser.getIp());
    }


//
//    @Override
//    public boolean doLogoutAll() {
//        Set keys = redisHelper.keys(loginKeyService.REDIS_LOGIN_USER + "*");
//        String[] arrays = (String[]) keys.stream().toArray(String[]::new);
//        redisHelper.del(arrays);
//        return true;
//    }
//
//    @Override
//    public String key(String userMu) {
//        return loginKeyService.key(userMu);
//    }
//
//    @Override
//    public String getRedisLoginUser() {
//        return loginKeyService.REDIS_LOGIN_USER;
//    }
//

}
