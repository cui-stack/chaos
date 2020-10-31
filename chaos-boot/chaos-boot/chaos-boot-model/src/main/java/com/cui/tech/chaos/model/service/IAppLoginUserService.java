package com.cui.tech.chaos.model.service;


import com.cui.tech.chaos.model.login.*;

/**
 * @author G.G
 * @date 2019/11/15 15:10
 */
public interface IAppLoginUserService {
    AppLoginUser selectByUsernameAndPassword(AppLoginDto loginDto);

}
