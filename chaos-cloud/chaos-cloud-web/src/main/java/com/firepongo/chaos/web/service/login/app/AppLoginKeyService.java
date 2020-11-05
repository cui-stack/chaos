package com.firepongo.chaos.web.service.login.app;

import com.firepongo.chaos.app.constant.Constants;
import com.firepongo.chaos.web.service.login.ILoginKeyService;
import org.springframework.stereotype.Component;

/**
 * @author G.G
 * @date 2020/5/19 17:45
 */
@Component
public class AppLoginKeyService implements ILoginKeyService {
    String REDIS_LOGIN_USER = Constants.APP_USER;

    @Override
    public String key(String token) {
        return REDIS_LOGIN_USER + "_" + token;
    }
}
