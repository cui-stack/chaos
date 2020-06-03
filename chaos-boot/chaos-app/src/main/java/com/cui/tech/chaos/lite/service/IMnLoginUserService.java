package com.cui.tech.chaos.lite.service;


import com.cui.tech.chaos.model.login.ManageLoginDto;
import com.cui.tech.chaos.model.login.ManageLoginUser;

/**
 * @author G.G
 * @date 2019/11/15 15:10
 */
public interface IMnLoginUserService {
    ManageLoginUser selectByUsernameAndPassword(ManageLoginDto loginDto);

}
