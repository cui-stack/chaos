package com.firepongo.chaos.admin.service.tran;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.firepongo.chaos.admin.api.data.ChaosAdminRoleData;
import com.firepongo.chaos.app.admin.ChaosRoleData;
import com.firepongo.chaos.admin.api.data.ChaosRoleResourceData;
import com.firepongo.chaos.admin.api.data.ChaosRoleResourceListData;
import com.firepongo.chaos.admin.api.entity.ChaosRoleResource;
import com.firepongo.chaos.admin.api.service.*;
import com.firepongo.chaos.app.admin.ChaosAdminData;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
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
    private IMnLoginUserService iMnLoginUserService;

    @Autowired
    private IChaosAdminService iChaosAdminService;

    @Autowired
    private IChaosRoleService iChaosRoleService;

    @Autowired
    private IChaosAdminRoleService iChaosAdminRoleService;

    @Autowired
    private IChaosResourceService iChaosResourceService;

    @Autowired
    private IChaosRoleResourceService iChaosRoleResourceService;

    @Transactional(rollbackFor = {Exception.class})
    public MU addAdmin(ChaosAdminData data) {
        try {
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
        String adminMu = data.getMu();
        String roleMu = data.getData().getRoleMu();
        try {
            boolean adminUpdated = dealUpdateAdmin(data);
            if (StringUtils.isEmpty(roleMu)) {
                return adminUpdated;
            }
            boolean roleUpdated = dealUpdateRole(adminMu, roleMu);
            return adminUpdated && roleUpdated;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    private boolean dealUpdateRole(String adminMu, String roleMu) {
        boolean roleUpdated = true;
        List<ChaosAdminRoleData> list = iChaosAdminRoleService.selectByData(ChaosAdminRoleData.of(adminMu));
        if (list.size() == 0) {
            iChaosAdminRoleService.insertModel(new ChaosAdminRoleData(adminMu, roleMu));
        } else if (list.size() == 1) {
            roleUpdated = iChaosAdminRoleService.updateRoleByAdmin(adminMu, roleMu);
        }
        return roleUpdated;
    }

    private boolean dealUpdateAdmin(UpdateData<ChaosAdminData> data) {
        if (!StringUtils.isEmpty(data.getData().getPassword())) {
            data.getData().setZip(data.getData().getPassword());
            data.getData().setPassword(DigestUtils.md5Hex(data.getData().getPassword()));
        } else {
            data.getData().setPassword(null);
        }
        return iChaosAdminService.updateModelByMU(data);
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean grant(ChaosRoleResourceListData data) {
        try {
            ArrayList<ChaosRoleResource> addList = (ArrayList) data.getAddMus().stream().map(resourceMu -> {
                ChaosRoleResource entity = new ChaosRoleResource();
                return entity.setRoleMu(data.getRoleMu()).setResourceMu(resourceMu);
            }).collect(Collectors.toList());
            boolean result = true;
            if (!CollectionUtils.isEmpty(addList)) {
                result = iChaosRoleResourceService.saveBatch(addList);
                if (!result) {
                    return false;
                }
            }
            if (!CollectionUtils.isEmpty(data.getDeleteMus())) {
                result = iChaosRoleResourceService.remove(new QueryWrapper<ChaosRoleResource>().lambda().in(ChaosRoleResource::getResourceMu, data.getDeleteMus()));
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
        if (chaosAdminRole == null) {
            return;
        }
        ChaosRoleData role = iChaosRoleService.selectByMU(MU.of(chaosAdminRole.getRoleMu()));
        user.setRoleName(role.getName());
        user.setRoleInfo(role.getInfo());
        user.setIndexLink(role.getIndexLink());
        List<ManageMenu> menus = iChaosResourceService.selectResourceByAdmin(user.getMu());
        user.setMenus(menus);
    }

    @Transactional(rollbackFor = {Exception.class})
    public ManageLoginUser doInitAdminByPhone(ManageLoginDto loginDto) {
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
    public boolean deleteAdmin(MU admin) {
        try {
            iChaosAdminService.removeById(admin.getMu());

            ChaosAdminRoleData card = new ChaosAdminRoleData();
            card.setAdminMu(admin.getMu());
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

    @Transactional(rollbackFor = {Exception.class})
    public boolean deleteRole(MU role) {
        try {
            iChaosRoleService.removeById(role.getMu());

            ChaosAdminRoleData card = new ChaosAdminRoleData();
            card.setRoleMu(role.getMu());
            List<ChaosAdminRoleData> cardList = iChaosAdminRoleService.selectByData(card);
            List cardIds = cardList.stream().map(d -> d.getMu()).collect(Collectors.toList());
            iChaosAdminRoleService.removeByIds(cardIds);

            ChaosRoleResourceData crpd = new ChaosRoleResourceData();
            crpd.setRoleMu(role.getMu());
            List<ChaosRoleResourceData> crpdList = iChaosRoleResourceService.selectByData(crpd);
            List crpdIds = crpdList.stream().map(d -> d.getMu()).collect(Collectors.toList());
            iChaosRoleResourceService.removeByIds(crpdIds);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("业务回滚", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean deleteResource(MU resourceMu) {
        try {
            iChaosResourceService.removeById(resourceMu.getMu());
            ChaosRoleResourceData crrd = new ChaosRoleResourceData();
            crrd.setResourceMu(resourceMu.getMu());
            List<ChaosRoleResourceData> crrdList = iChaosRoleResourceService.selectByData(crrd);
            List crrdIds = crrdList.stream().map(d -> d.getMu()).collect(Collectors.toList());
            iChaosRoleResourceService.removeByIds(crrdIds);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("业务回滚", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }
}
