package com.firepongo.chaos.admin.service.tran;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.firepongo.chaos.admin.api.data.ChaosAdminData;
import com.firepongo.chaos.admin.api.data.ChaosAdminRoleData;
import com.firepongo.chaos.admin.api.data.ChaosRolePermissionListData;
import com.firepongo.chaos.admin.api.entity.ChaosRolePermission;
import com.firepongo.chaos.admin.api.service.IChaosAdminRoleService;
import com.firepongo.chaos.admin.api.service.IChaosAdminService;
import com.firepongo.chaos.admin.api.service.IChaosRolePermissionService;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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

    @Transactional(rollbackFor = {Exception.class})
    public boolean addAdmin(ChaosAdminData data) {
        try {
            log.info("添加运营账户");
            data.setZip(data.getPassword());
            data.setPassword(DigestUtils.md5Hex(data.getPassword()));
            MU mu = iChaosAdminService.insertModel(data);
            ChaosAdminRoleData entity = new ChaosAdminRoleData();
            iChaosAdminRoleService.insertModel(entity.setAdminMu(mu.getMu()).setRoleMu(data.getRoleMu()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean updateAdminRole(UpdateData<ChaosAdminData> data) {
        try {
            log.info("");
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
            log.error("", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean grant(ChaosRolePermissionListData data) {
        try {
            ArrayList<ChaosRolePermission> addList = (ArrayList) data.getAddMus().stream().map(permissionMu -> {
                ChaosRolePermission entity =new ChaosRolePermission();
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
}
