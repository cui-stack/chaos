package com.cui.tech.chaos.manage.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.tech.chaos.manage.api.data.ChaosLinkData;
import com.cui.tech.chaos.manage.api.entity.ChaosLink;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;

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
