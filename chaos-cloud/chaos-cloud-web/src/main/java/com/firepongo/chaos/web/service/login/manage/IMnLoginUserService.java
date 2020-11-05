package com.firepongo.chaos.web.service.login.manage;

import com.firepongo.chaos.app.login.manage.ManageLoginDto;
import com.firepongo.chaos.app.login.manage.ManageLoginUser;

/**
 * @author G.G
 * @date 2019/11/15 15:10
 */
public interface IMnLoginUserService {
    ManageLoginUser selectByUsernameAndPassword(ManageLoginDto loginDto);

}
