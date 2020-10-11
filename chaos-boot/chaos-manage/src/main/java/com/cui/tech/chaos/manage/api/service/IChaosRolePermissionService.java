package com.cui.tech.chaos.manage.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.tech.chaos.manage.api.data.ChaosRolePermissionListData;
import com.cui.tech.chaos.manage.api.entity.ChaosRolePermission;
import com.cui.tech.chaos.manage.api.data.ChaosRolePermissionData;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;

import java.util.List;

/**
 * @author G.G
 * @since 2020-04-14
 */
public interface IChaosRolePermissionService extends IService<ChaosRolePermission> {

    MU insertModel(ChaosRolePermissionData data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<ChaosRolePermissionData> data);

    ChaosRolePermission selectByMU(MU data);

    List<ChaosRolePermission> selectByData(ChaosRolePermissionData data);

    PageList<ChaosRolePermission> selectByPage(PageQueryDto<ChaosRolePermissionData> pageData);

    List<ChaosRolePermissionData> selectRolePermissionByData(ChaosRolePermissionData data);

    List<String> selectPermissionMusByRoleMu(ChaosRolePermissionData data);

    boolean grant(ChaosRolePermissionListData data);
}
