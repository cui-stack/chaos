package com.firepongo.chaos.admin.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.admin.api.data.ChaosPlatformData;
import com.firepongo.chaos.admin.api.entity.ChaosPlatform;
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

    ChaosPlatformData selectByMU(MU data);

    List<ChaosPlatformData> selectByData(ChaosPlatformData data);

    PageList<ChaosPlatformData> selectByPage(PageQueryDto<ChaosPlatformData> pageData);
}
