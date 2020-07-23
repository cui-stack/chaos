package com.cui.tech.service.service;

import com.cui.tech.chaos.lite.service.IMnLoginUserService;
import com.cui.tech.chaos.lite.service.ManageLoginServiceImpl;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.login.LoginUser;
import com.cui.tech.chaos.model.login.ManageLoginDto;
import com.cui.tech.chaos.model.login.ManageLoginUser;
import com.cui.tech.chaos.model.role.ManageLoginUserRole;
import com.cui.tech.manage.api.data.ChaosAdminData;
import com.cui.tech.manage.api.entity.ChaosAdminRole;
import com.cui.tech.manage.api.entity.ChaosRole;
import com.cui.tech.manage.api.service.IChaosAdminRoleService;
import com.cui.tech.manage.api.service.IChaosAdminService;
import com.cui.tech.manage.api.service.IChaosRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author G.G
 * @date 2019/11/14 15:45
 */
@Component("mnLoginService")
public class ManageLoginService extends ManageLoginServiceImpl {
    @Autowired
    private IMnLoginUserService iMnLoginUserService;
    @Autowired
    private IChaosAdminService iChaosAdminService;
    @Autowired
    private IChaosAdminRoleService iChaosAdminRoleService;
    @Autowired
    private IChaosRoleService iChaosRoleService;

    @Override
    public ManageLoginUser getUserInfo(ManageLoginDto loginDto) {
        ManageLoginUser user = iMnLoginUserService.selectByUsernameAndPassword(loginDto);
        if (user == null) {
            return null;
        }
        ChaosAdminRole chaosAdminRole = iChaosAdminRoleService.selectByAdmin(user.getMu());
        ChaosRole role = iChaosRoleService.selectByMU(new MU(chaosAdminRole.getRoleMu()));
        user.setRole(new ManageLoginUserRole(role.getName(), role.getInfo(), role.getIndexLink()));
        return user;
    }

    @Override
    public void afterLogin(LoginUser loginUser) {
        iChaosAdminService.updateLoginLog(new UpdateData<ChaosAdminData>(loginUser.getMu(), ChaosAdminData.of(loginUser.getIp())));
    }
}
