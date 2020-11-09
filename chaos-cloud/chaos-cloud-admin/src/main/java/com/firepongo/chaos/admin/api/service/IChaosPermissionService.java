package com.firepongo.chaos.admin.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.admin.api.entity.ChaosPermission;
import com.firepongo.chaos.admin.api.data.ChaosPermissionData;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.login.manage.ManageMenu;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;

import java.util.List;

/**
 *
 * @author G.G
 * @since 2020-04-14
*/
public interface IChaosPermissionService extends IService<ChaosPermission> {

    MU insertModel(ChaosPermissionData data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<ChaosPermissionData> data);

    ChaosPermissionData selectByMU(MU data);

    List<ChaosPermissionData> selectByData(ChaosPermissionData data);

    PageList<ChaosPermissionData> selectByPage(PageQueryDto<ChaosPermissionData> pageData);

    List<ManageMenu> selectPermissionByAdmin(String adminmu);

    PageList<ChaosPermissionData> selectBySortPage(PageQueryDto<ChaosPermissionData> pageData);


}
