package com.firepongo.chaos.web.service.login;

import com.firepongo.chaos.app.login.LoginDto;
import com.firepongo.chaos.app.login.LoginUser;

import java.util.Date;

/**
 * @author G.G
 * @date 2020/5/14 10:35
 */
public abstract class BaseLoginServiceImpl implements ILoginService {

    @Override
    public LoginUser doLogin(LoginDto loginDto) {
        //填充ip,mu,以及各端扩展字段给loginDto
        LoginUser loginUser = befoLogin(loginDto);
        if (loginUser == null) {
            return null;
        }
        loginUser.setIp(loginDto.getIp());
        loginUser.setLoginTime(new Date());
        dealRedisToken(loginUser);
        afterLogin(loginUser);
        return loginUser;
    }

    protected abstract LoginUser befoLogin(LoginDto loginDto);

    protected abstract void afterLogin(LoginUser loginUser);

    protected abstract void dealRedisToken(LoginUser loginUser);

//    public boolean doLogout(String msg) {
//        //redisHelper.del(key(msg));
//        return true;
//    }

//    @Override
//    public List loginUsers() {
//        Set keys = redisHelper.keys(getRedisLoginUser() + "*");
//        ArrayList list = (ArrayList) keys.stream().map(key -> redisHelper.get((String) key)).collect(Collectors.toList());
//        return list;
//    }
//    @Override
//    public LoginUser getLoginUser(String msg) {
//        if (StringUtils.isEmpty(msg)) {
//            return null;
//        }
//        return(LoginUser) redisHelper.get(key(msg));
//    }

}
