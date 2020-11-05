package com.firepongo.chaos.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.firepongo.chaos.admin.api.data.ChaosRoleData;
import com.firepongo.chaos.admin.api.entity.ChaosRole;
import com.firepongo.chaos.admin.api.service.IChaosRoleService;
import com.firepongo.chaos.admin.service.mapper.ChaosRoleMapper;
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
@Service(interfaceClass = IChaosRoleService.class)
@Component
public class ChaosRoleServiceImpl extends ServiceImpl<ChaosRoleMapper, ChaosRole> implements IChaosRoleService {
    @Autowired
    private ConvertService convertService;

    @Override
    public MU insertModel(ChaosRoleData data) {
        ChaosRole entity = (ChaosRole) convertService.convertToMuModel(data, ChaosRole.class);
        entity.setVersion(1);
        return save(entity) ? MU.of(entity.getMu()) : null;
    }

    @Override
    public boolean deleteModel(MU data) {
        return removeById(data.getMu());
    }

    @Override
    public boolean updateModelByMU(UpdateData<ChaosRoleData> data) {
        ChaosRole entity = (ChaosRole) convertService.convertToMuModel(data.getData(), ChaosRole.class);
        return update(entity, new UpdateWrapper<ChaosRole>().eq(Table.MU, data.getMu()));
    }

    @Override
    public ChaosRoleData selectByMU(MU data) {
        return (ChaosRoleData) convertService.convertToDTO(getOne(new QueryWrapper<ChaosRole>()
                .eq(Table.MU, data.getMu())), ChaosRoleData.class);
    }

    @Override
    public List<ChaosRoleData> selectByData(ChaosRoleData data) {
        QueryWrapper<ChaosRole> query = new QueryWrapper<ChaosRole>();
        query.orderByDesc(Table.ID);
        return convertService.convertToDTO(list(query), ChaosRoleData.class);
    }

    @Override
    public PageList<ChaosRoleData> selectByPage(PageQueryDto<ChaosRoleData> pageData) {
        QueryWrapper<ChaosRole> query = new QueryWrapper();
        query.lambda()
                .eq(!StringUtils.isEmpty(pageData.getData().getInfo()), ChaosRole::getInfo, pageData.getData().getInfo())
                .eq(!StringUtils.isEmpty(pageData.getData().getPlatformMu()), ChaosRole::getPlatformMu, pageData.getData().getPlatformMu());
        query.orderByDesc(Table.ID);
        return new PageList(page(new Page(pageData.getPageNum(), pageData.getPageSize()), query));
    }



}
