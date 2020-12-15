package com.firepongo.chaos.manager.service;

import com.firepongo.chaos.app.login.LoginDto;
import com.firepongo.chaos.app.login.LoginUser;
import com.firepongo.chaos.app.login.manage.ManageLoginDto;
import com.firepongo.chaos.app.login.manage.ManageLoginUser;
import com.firepongo.chaos.web.service.AdminPlatformService;
import com.firepongo.chaos.web.service.login.manage.ManageLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author G.G
 * @date 2019/11/14 15:45
 */
@Component("mnLoginService")
public class ManageLoginService extends ManageLoginServiceImpl {
    @Autowired
    private AdminPlatformService adminPlatformService;

    @Override
    protected LoginUser befoLogin(LoginDto loginDto) {
        ManageLoginDto mld = (ManageLoginDto) loginDto;
        ManageLoginUser mlu = adminPlatformService.userLogin(mld.getUsername(), mld.getPassword(),"2");
        if (mlu == null || mlu.getStatus() == 1) {
            return null;
        }
        return mlu;
    }

}
