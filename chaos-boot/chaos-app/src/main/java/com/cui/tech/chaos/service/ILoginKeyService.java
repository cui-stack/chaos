package com.cui.tech.chaos.service;

import lombok.Getter;

/**
 * @author G.G
 * @date 2020/5/19 17:43
 */
public interface ILoginKeyService {
    String REDIS_LOGIN_USER = "";

    String key(String msg);

}
