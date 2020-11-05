package com.firepongo.chaos.admin.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.admin.api.data.ChaosLinkData;
import com.firepongo.chaos.admin.api.entity.ChaosLink;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;

import java.util.List;

/**
 *
 * @author G.G
 * @since 2020-08-17
*/
public interface IChaosLinkService extends IService<ChaosLink> {

    MU insertModel(ChaosLinkData data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<ChaosLinkData> data);

    ChaosLinkData selectByMU(MU data);

    List<ChaosLinkData> selectByData(ChaosLinkData data);

    PageList<ChaosLinkData> selectByPage(PageQueryDto<ChaosLinkData> pageData);
}
