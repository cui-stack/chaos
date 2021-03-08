package com.firepongo.chaos.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.firepongo.chaos.admin.api.data.ChaosRoleResourceData;
import com.firepongo.chaos.admin.api.entity.ChaosRoleResource;
import com.firepongo.chaos.admin.service.mapper.ChaosRoleResourceMapper;
import com.firepongo.chaos.admin.api.service.IChaosRoleResourceService;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.Table;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.app.service.ConvertService;
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
 * @since 2020-04-14
 */
@Service(interfaceClass = IChaosRoleResourceService.class)
@Component
public class ChaosRoleResourceServiceImpl extends ServiceImpl<ChaosRoleResourceMapper, ChaosRoleResource> implements IChaosRoleResourceService {
    @Autowired
    private ConvertService convertService;

    @Override
    public MU insertModel(ChaosRoleResourceData data) {
        ChaosRoleResource entity = (ChaosRoleResource) convertService.convertToMuModel(data, ChaosRoleResource.class);
        return save(entity) ? MU.of(entity.getMu()) : null;
    }

    @Override
    public boolean deleteModel(MU data) {
        return removeById(data.getMu());
    }

    @Override
    public boolean updateModelByMU(UpdateData<ChaosRoleResourceData> data) {
        ChaosRoleResource entity = (ChaosRoleResource) convertService.convertToMuModel(data.getData(), ChaosRoleResource.class);
        return update(entity, new UpdateWrapper<ChaosRoleResource>().eq(Table.MU, data.getMu()));
    }

    @Override
    public ChaosRoleResourceData selectByMU(MU data) {
        return (ChaosRoleResourceData) convertService.convertToDTO(getOne(new QueryWrapper<ChaosRoleResource>()
                .eq(Table.MU, data.getMu())), ChaosRoleResourceData.class);
    }

    @Override
    public List<ChaosRoleResourceData> selectByData(ChaosRoleResourceData data) {
        QueryWrapper<ChaosRoleResource> query = new QueryWrapper<ChaosRoleResource>();
        query.lambda()
                .eq(!StringUtils.isEmpty(data.getRoleMu()), ChaosRoleResource::getRoleMu, data.getRoleMu())
                .eq(!StringUtils.isEmpty(data.getResourceMu()), ChaosRoleResource::getResourceMu, data.getResourceMu());
        query.orderByDesc(Table.ID);
        return convertService.convertToDTO(list(query), ChaosRoleResourceData.class);
    }

    @Override
    public PageList<ChaosRoleResourceData> selectByPage(PageQueryDto<ChaosRoleResourceData> pageData) {
        QueryWrapper query = new QueryWrapper();
        query.orderByDesc(Table.ID);
        return new PageList(page(new Page(pageData.getPageNum(), pageData.getPageSize()), query));
    }

    @Override
    public List<ChaosRoleResourceData> selectRoleResourceByData(ChaosRoleResourceData data) {
        return baseMapper.selectRoleResourceByData(data.getRoleMu());
    }

    @Override
    public List<String> selectResourceMusByRoleMu(ChaosRoleResourceData data) {
        return baseMapper.selectResourceMusByRoleMu(data.getRoleMu());
    }




}
