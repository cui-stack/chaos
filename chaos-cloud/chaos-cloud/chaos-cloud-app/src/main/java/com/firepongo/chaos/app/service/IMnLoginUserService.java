package com.firepongo.chaos.app.service;


import com.firepongo.chaos.app.login.ManageLoginDto;
import com.firepongo.chaos.app.login.ManageLoginUser;

/**
 * @author G.G
 * @date 2019/11/15 15:10
 */
public interface IMnLoginUserService {
    ManageLoginUser selectByUsernameAndPassword(ManageLoginDto loginDto);

}
