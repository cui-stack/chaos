package com.firepongo.chaos.admin.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.admin.api.entity.ChaosRoleResource;
import com.firepongo.chaos.admin.api.data.ChaosRoleResourceData;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;

import java.util.List;

/**
 * @author G.G
 * @since 2020-04-14
 */
public interface IChaosRoleResourceService extends IService<ChaosRoleResource> {

    MU insertModel(ChaosRoleResourceData data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<ChaosRoleResourceData> data);

    ChaosRoleResourceData selectByMU(MU data);

    List<ChaosRoleResourceData> selectByData(ChaosRoleResourceData data);

    PageList<ChaosRoleResourceData> selectByPage(PageQueryDto<ChaosRoleResourceData> pageData);

    List<ChaosRoleResourceData> selectRoleResourceByData(ChaosRoleResourceData data);

    List<String> selectResourceMusByRoleMu(ChaosRoleResourceData data);

}
