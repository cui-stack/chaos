package com.cui.tech.manage.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.tech.manage.api.entity.ChaosPlatform;
import com.cui.tech.manage.api.data.ChaosPlatformData;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;

import java.util.List;

/**
 *
 * @author G.G
 * @since 2020-04-23
*/
public interface IChaosPlatformService extends IService<ChaosPlatform> {

    MU insertModel(ChaosPlatformData data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<ChaosPlatformData> data);

    ChaosPlatform selectByMU(MU data);

    List<ChaosPlatform> selectByData(ChaosPlatformData data);

    PageList<ChaosPlatform> selectByPage(PageQueryDto<ChaosPlatformData> pageData);
}
