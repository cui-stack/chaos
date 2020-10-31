package com.cui.tech.chaos.web.service;

import com.cui.tech.chaos.model.Constants;
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
