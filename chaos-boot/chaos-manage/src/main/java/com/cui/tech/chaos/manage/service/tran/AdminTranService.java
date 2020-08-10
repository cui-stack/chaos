package com.cui.tech.chaos.manage.service.tran;

import com.cui.tech.chaos.manage.api.data.ChaosAdminData;
import com.cui.tech.chaos.manage.api.data.ChaosAdminRoleData;
import com.cui.tech.chaos.manage.api.service.IChaosAdminRoleService;
import com.cui.tech.chaos.manage.api.service.IChaosAdminService;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

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

    @Transactional(rollbackFor = {Exception.class})
    public boolean addAdmin(ChaosAdminData data) {
        try {
            log.info("添加运营账户");
            data.setZip(data.getPassword());
            data.setPassword(DigestUtils.md5Hex(data.getPassword()));
            MU mu = iChaosAdminService.insertModel(data);
            iChaosAdminRoleService.insertModel(new ChaosAdminRoleData(mu.getMu(), data.getRoleMu()));
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
}
