package com.firepongo.chaos.admin.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.admin.api.data.ChaosRolePermissionListData;
import com.firepongo.chaos.admin.api.entity.ChaosRolePermission;
import com.firepongo.chaos.admin.api.data.ChaosRolePermissionData;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;

import java.util.List;

/**
 * @author G.G
 * @since 2020-04-14
 */
public interface IChaosRolePermissionService extends IService<ChaosRolePermission> {

    MU insertModel(ChaosRolePermissionData data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<ChaosRolePermissionData> data);

    ChaosRolePermissionData selectByMU(MU data);

    List<ChaosRolePermissionData> selectByData(ChaosRolePermissionData data);

    PageList<ChaosRolePermissionData> selectByPage(PageQueryDto<ChaosRolePermissionData> pageData);

    List<ChaosRolePermissionData> selectRolePermissionByData(ChaosRolePermissionData data);

    List<String> selectPermissionMusByRoleMu(ChaosRolePermissionData data);

}
