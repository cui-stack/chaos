package com.firepongo.chaos.web.service;

import com.firepongo.chaos.app.constant.Constants;
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
