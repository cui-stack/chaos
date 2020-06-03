package com.cui.tech.chaos.lite.service;

import com.cui.tech.chaos.model.Constants;
import org.springframework.stereotype.Component;

/**
 * @author G.G
 * @date 2020/5/19 17:45
 */
@Component
public class ManageLoginKeyService implements ILoginKeyService {
    String REDIS_LOGIN_USER = Constants.MANAGE_USER;

    @Override
    public String key(String userMu) {
        return REDIS_LOGIN_USER + "_" + userMu;
    }
}
