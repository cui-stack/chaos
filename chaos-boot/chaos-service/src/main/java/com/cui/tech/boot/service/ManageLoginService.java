package com.cui.tech.boot.service;

import com.cui.tech.chaos.model.login.LoginUser;
import com.cui.tech.chaos.model.login.ManageLoginDto;
import com.cui.tech.chaos.model.login.ManageLoginUser;
import com.cui.tech.chaos.model.role.ManageLoginUserRole;
import com.cui.tech.chaos.model.role.Role;
import com.cui.tech.chaos.service.ManageLoginServiceImpl;
import org.springframework.stereotype.Component;

/**
 * @author G.G
 * @date 2019/11/14 15:45
 */
@Component("mnLoginService")
public class ManageLoginService extends ManageLoginServiceImpl {

    @Override
    public ManageLoginUser getUserInfo(ManageLoginDto loginDto) {
        ManageLoginUser user = new ManageLoginUser();
        ManageLoginUserRole role = new ManageLoginUserRole();
        role.setName(Role.ADMIN.name);
        role.setInfo(Role.ADMIN.name);
        role.setIndexLink("/index");
        user.setRole(role);
        user.setUsername("golden");
        user.setPhone("18866668888");
        return user;
    }

    @Override
    public void afterLogin(LoginUser loginUser) {
    }
}
