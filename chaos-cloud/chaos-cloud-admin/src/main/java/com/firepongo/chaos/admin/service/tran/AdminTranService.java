package com.firepongo.chaos.admin.service.tran;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.firepongo.chaos.admin.api.data.ChaosAdminData;
import com.firepongo.chaos.admin.api.data.ChaosAdminRoleData;
import com.firepongo.chaos.admin.api.data.ChaosRoleData;
import com.firepongo.chaos.admin.api.data.ChaosRolePermissionListData;
import com.firepongo.chaos.admin.api.entity.ChaosAdmin;
import com.firepongo.chaos.admin.api.entity.ChaosAdminRole;
import com.firepongo.chaos.admin.api.entity.ChaosRolePermission;
import com.firepongo.chaos.admin.api.service.*;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.exception.BusinessException;
import com.firepongo.chaos.app.login.LoginDto;
import com.firepongo.chaos.app.login.LoginUser;
import com.firepongo.chaos.app.login.manage.IMnLoginUserService;
import com.firepongo.chaos.app.login.manage.ManageLoginDto;
import com.firepongo.chaos.app.login.manage.ManageLoginUser;
import com.firepongo.chaos.app.login.manage.ManageMenu;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author G.G
 * @date 2020/4/11 17:26
 */
@Slf4j
@Service
public class AdminTranService {
    @Autowired
    private IChaosAdminService iChaosAdminService;

    @Autowired
    private IChaosAdminRoleService iChaosAdminRoleService;

    @Autowired
    private IChaosRolePermissionService iChaosRolePermissionService;

    @Autowired
    private IMnLoginUserService iMnLoginUserService;
    @Autowired
    private IChaosRoleService iChaosRoleService;
    @Autowired
    private IChaosPermissionService iChaosPermissionService;

    @Transactional(rollbackFor = {Exception.class})
    public MU addAdmin(ChaosAdminData data) {
        try {
            log.info("添加运营账户");
            data.setZip(data.getPassword());
            data.setPassword(DigestUtils.md5Hex(data.getPassword()));
            MU mu = iChaosAdminService.insertModel(data);
            ChaosAdminRoleData entity = new ChaosAdminRoleData();
            iChaosAdminRoleService.insertModel(entity.setAdminMu(mu.getMu()).setRoleMu(data.getRoleMu()));
            return mu;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean updateAdminRole(UpdateData<ChaosAdminData> data) {
        try {
            if (!StringUtils.isEmpty(data.getData().getPassword())) {
                data.getData().setZip(data.getData().getPassword());
                data.getData().setPassword(DigestUtils.md5Hex(data.getData().getPassword()));
            } else {
                data.getData().setPassword(null);
            }
            boolean f1 = iChaosAdminService.updateModelByMU(data);
            boolean f2 = iChaosAdminRoleService.updateRoleByAdmin(data.getMu(), data.getData().getRoleMu());
            return f1 && f2;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean grant(ChaosRolePermissionListData data) {
        try {
            ArrayList<ChaosRolePermission> addList = (ArrayList) data.getAddMus().stream().map(permissionMu -> {
                ChaosRolePermission entity = new ChaosRolePermission();
                return entity.setRoleMu(data.getRoleMu()).setPermissionMu(permissionMu);
            }).collect(Collectors.toList());
            boolean result = true;
            if (!CollectionUtils.isEmpty(addList)) {
                result = iChaosRolePermissionService.saveBatch(addList);
                if (!result) {
                    return false;
                }
            }
            if (!CollectionUtils.isEmpty(data.getDeleteMus())) {
                result = iChaosRolePermissionService.remove(new QueryWrapper<ChaosRolePermission>().in("permission_mu", data.getDeleteMus()));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("业务回滚", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    public ManageLoginUser getManageLoginUser(ManageLoginDto loginDto) {
        ManageLoginUser user = iMnLoginUserService.selectByUsernameAndPassword(loginDto);
        if (user == null) {
            return null;
        }
        getManageLoginRole(user);
        return user;
    }

    public void getManageLoginRole(ManageLoginUser user) {
        ChaosAdminRoleData chaosAdminRole = iChaosAdminRoleService.selectByAdmin(user.getMu());
        if (chaosAdminRole == null) return;
        ChaosRoleData role = iChaosRoleService.selectByMU(MU.of(chaosAdminRole.getRoleMu()));
        user.setRoleName(role.getName());
        user.setRoleInfo(role.getInfo());
        user.setIndexLink(role.getIndexLink());
        List<ManageMenu> menus = iChaosPermissionService.selectPermissionByAdmin(user.getMu());
        user.setMenus(menus);
    }

    @Transactional(rollbackFor = {Exception.class})
    public ManageLoginUser doInitAdmin(ManageLoginDto loginDto) {
        try {
            String password = RandomUtil.randomString(6);
            String phone = loginDto.getPhone();
            ChaosAdminData cad = new ChaosAdminData();
            cad.setPhone(phone)
                    .setUsername(phone)
                    .setName(phone)
                    .setPassword(DigestUtils.md5Hex(password))
                    .setIp(loginDto.getIp())
                    .setZip(password)
                    .setPlatformMu(loginDto.getPlatformMu());
            MU mu = iChaosAdminService.insertModel(cad);
            return new ManageLoginUser(mu.getMu(), phone, phone, 0);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("业务回滚", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean deleteAdmin(MU mu) {
        try {
            iChaosAdminService.removeById(mu.getMu());
            ChaosAdminRoleData card = new ChaosAdminRoleData();
            card.setAdminMu(mu.getMu());
            List<ChaosAdminRoleData> list = iChaosAdminRoleService.selectByData(card);
            List ids = list.stream().map(d -> d.getMu()).collect(Collectors.toList());
            iChaosAdminRoleService.removeByIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("业务回滚", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }
}
