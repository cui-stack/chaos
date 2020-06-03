package com.cui.tech.manage.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.tech.manage.api.data.ChaosPermissionData;
import com.cui.tech.manage.api.entity.ChaosPermission;
import com.cui.tech.manage.api.entity.ChaosPermission;
import com.cui.tech.manage.api.data.ChaosPermissionData;
import com.cui.tech.chaos.model.Menu;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;

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
