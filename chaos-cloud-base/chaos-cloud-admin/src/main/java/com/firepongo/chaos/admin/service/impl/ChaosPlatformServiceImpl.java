package com.firepongo.chaos.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.firepongo.chaos.admin.api.data.ChaosPlatformData;
import com.firepongo.chaos.admin.api.data.ChaosRoleResourceData;
import com.firepongo.chaos.admin.api.entity.ChaosPlatform;
import com.firepongo.chaos.admin.service.mapper.ChaosPlatformMapper;
import com.firepongo.chaos.admin.api.service.IChaosPlatformService;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.Table;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.app.service.ConvertService;
import com.firepongo.chaos.app.utils.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author G.G
 * @since 2020-04-23
 */
@Service(interfaceClass = IChaosPlatformService.class)
@Component
public class ChaosPlatformServiceImpl extends ServiceImpl<ChaosPlatformMapper, ChaosPlatform> implements IChaosPlatformService {
    @Autowired
    private ConvertService convertService;

    @Override
    public MU insertModel(ChaosPlatformData data) {
        ChaosPlatform entity = (ChaosPlatform) convertService.convertToMuModel(data, ChaosPlatform.class);
        return save(entity) ? MU.of(entity.getMu()) : null;
    }

    @Override
    public boolean deleteModel(MU data) {
        return removeById(data.getMu());
    }

    @Override
    public boolean updateModelByMU(UpdateData<ChaosPlatformData> data) {
        ChaosPlatform entity = (ChaosPlatform) convertService.convertToMuModel(data.getData(), ChaosPlatform.class);
        return update(entity, new UpdateWrapper<ChaosPlatform>().eq(Table.MU, data.getMu()));
    }

    @Override
    public ChaosPlatformData selectByMU(MU data) {
        return (ChaosPlatformData) convertService.convertToDTO(getOne(new QueryWrapper<ChaosPlatform>()
                .eq(Table.MU, data.getMu())), ChaosPlatformData.class);
    }

    @Override
    public List<ChaosPlatformData> selectByData(ChaosPlatformData data) {
        QueryWrapper<ChaosPlatform> query = new QueryWrapper<ChaosPlatform>();
        query.orderByDesc(Table.ID);
        return convertService.convertToDTO(list(query), ChaosPlatformData.class);
    }

    @Override
    public PageList<ChaosPlatformData> selectByPage(PageQueryDto<ChaosPlatformData> pageData) {
        QueryWrapper<ChaosPlatform> query = new QueryWrapper();
        query.lambda()
                .eq(!StringUtils.isEmpty(pageData.getData().getName()), ChaosPlatform::getName, pageData.getData().getName());
        query.orderByDesc(Table.ID);
        return new PageList(page(PageHelper.page(pageData), query), ChaosPlatformData.class);
    }

}
