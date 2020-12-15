package com.firepongo.chaos.admin.service;

import com.firepongo.chaos.admin.api.data.ChaosAdminRoleData;
import com.firepongo.chaos.admin.api.service.IChaosAdminRoleService;
import com.firepongo.chaos.admin.api.service.IChaosAdminService;
import com.firepongo.chaos.admin.api.service.IChaosPermissionService;
import com.firepongo.chaos.admin.api.service.IChaosRoleService;
import com.firepongo.chaos.app.admin.ChaosAdminData;
import com.firepongo.chaos.app.admin.ChaosRoleData;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.login.LoginDto;
import com.firepongo.chaos.app.login.LoginUser;
import com.firepongo.chaos.app.login.manage.IMnLoginUserService;
import com.firepongo.chaos.app.login.manage.ManageLoginDto;
import com.firepongo.chaos.app.login.manage.ManageLoginUser;
import com.firepongo.chaos.app.login.manage.ManageMenu;
import com.firepongo.chaos.web.service.login.manage.ManageLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    @Autowired
    private IChaosPermissionService iChaosPermissionService;

    @Override
    protected LoginUser befoLogin(LoginDto loginDto) {
        ManageLoginDto manageLoginDto = (ManageLoginDto) loginDto;
        ManageLoginUser user = iMnLoginUserService.selectByUsernameAndPassword(manageLoginDto);
        if (user == null) {
            return null;
        }
        ChaosAdminRoleData chaosAdminRole = iChaosAdminRoleService.selectByAdmin(user.getMu());
        ChaosRoleData role = iChaosRoleService.selectByMU(MU.of(chaosAdminRole.getRoleMu()));
        user.setRoleName(role.getName());
        user.setRoleInfo(role.getInfo());
        user.setIndexLink(role.getIndexLink());
        List<ManageMenu> menus = iChaosPermissionService.selectPermissionByAdmin(user.getMu());
        user.setMenus(menus);
        return user;
    }

    @Override
    public void afterLogin(LoginUser loginUser) {
        iChaosAdminService.updateLoginLog(new UpdateData<ChaosAdminData>(loginUser.getMu(), new ChaosAdminData().setIp(loginUser.getIp())));
    }

}
