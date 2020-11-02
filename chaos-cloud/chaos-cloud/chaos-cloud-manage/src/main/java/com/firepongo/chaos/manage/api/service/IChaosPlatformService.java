package com.firepongo.chaos.manage.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.manage.api.data.ChaosPlatformData;
import com.firepongo.chaos.manage.api.entity.ChaosPlatform;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;

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
