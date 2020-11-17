package com.firepongo.chaos.web.service.login;

import com.firepongo.chaos.app.login.LoginDto;
import com.firepongo.chaos.app.login.LoginUser;
import com.firepongo.chaos.web.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author G.G
 * @date 2020/5/14 10:35
 */
public abstract class BaseLoginServiceImpl implements ILoginService {
    @Autowired
    protected RedisService redisService;

    @Override
    public LoginUser doLogin(LoginDto loginDto) {
        //填充ip,mu,以及各端扩展字段给loginDto
        LoginUser loginUser = befoLogin(loginDto);
        if (loginUser == null) {
            return null;
        }
        loginUser.setIp(loginDto.getIp());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        String now = df.format(time);
        loginUser.setLoginTime(now);
        dealRedisToken(loginUser);
        afterLogin(loginUser);
        return loginUser;
    }

    protected abstract LoginUser befoLogin(LoginDto loginDto);

    protected abstract void afterLogin(LoginUser loginUser);

    protected abstract void dealRedisToken(LoginUser loginUser);

    @Override
    public boolean doLogout(String msg) {
        redisService.del(msg);
        return true;
    }


}
