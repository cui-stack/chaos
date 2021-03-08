package com.firepongo.chaos.admin.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.admin.api.entity.ChaosResource;
import com.firepongo.chaos.admin.api.data.ChaosResourceData;
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
public interface IChaosResourceService extends IService<ChaosResource> {

    MU insertModel(ChaosResourceData data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<ChaosResourceData> data);

    ChaosResourceData selectByMU(MU data);

    List<ChaosResourceData> selectByData(ChaosResourceData data);

    PageList<ChaosResourceData> selectByPage(PageQueryDto<ChaosResourceData> pageData);

    List<ManageMenu> selectResourceByAdmin(String adminMu);

    PageList<ChaosResourceData> selectBySortPage(PageQueryDto<ChaosResourceData> pageData);


}
