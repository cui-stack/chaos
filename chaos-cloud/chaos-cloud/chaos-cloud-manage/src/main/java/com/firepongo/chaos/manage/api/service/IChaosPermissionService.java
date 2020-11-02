package com.firepongo.chaos.manage.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.manage.api.entity.ChaosPermission;
import com.firepongo.chaos.manage.api.data.ChaosPermissionData;
import com.firepongo.chaos.app.Menu;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
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

    ChaosPermission selectByMU(MU data);

    List<ChaosPermission> selectByData(ChaosPermissionData data);

    PageList<ChaosPermission> selectByPage(PageQueryDto<ChaosPermissionData> pageData);

    List<Menu> selectPermissionByAdmin(String adminmu);

    PageList<ChaosPermission> selectBySortPage(PageQueryDto<ChaosPermissionData> pageData);


}
