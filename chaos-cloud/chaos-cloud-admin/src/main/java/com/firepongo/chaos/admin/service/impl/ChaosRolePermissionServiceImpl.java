package com.firepongo.chaos.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.firepongo.chaos.admin.api.data.ChaosRolePermissionData;
import com.firepongo.chaos.admin.api.data.ChaosRolePermissionListData;
import com.firepongo.chaos.admin.api.entity.ChaosAdminRole;
import com.firepongo.chaos.admin.api.entity.ChaosRolePermission;
import com.firepongo.chaos.admin.api.entity.ChaosRolePermission;
import com.firepongo.chaos.admin.api.service.IChaosPermissionService;
import com.firepongo.chaos.admin.service.mapper.ChaosRolePermissionMapper;
import com.firepongo.chaos.admin.api.data.ChaosRolePermissionData;
import com.firepongo.chaos.admin.api.service.IChaosRolePermissionService;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.Table;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.app.service.ConvertService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author G.G
 * @since 2020-04-14
 */
@Service(interfaceClass = IChaosRolePermissionService.class)
@Component
public class ChaosRolePermissionServiceImpl extends ServiceImpl<ChaosRolePermissionMapper, ChaosRolePermission> implements IChaosRolePermissionService {
    @Autowired
    private ConvertService convertService;

    @Override
    public MU insertModel(ChaosRolePermissionData data) {
        ChaosRolePermission entity = (ChaosRolePermission) convertService.convertToMuModel(data, ChaosRolePermission.class);
        entity.setVersion(1);
        return save(entity) ? MU.of(entity.getMu()) : null;
    }

    @Override
    public boolean deleteModel(MU data) {
        return removeById(data.getMu());
    }

    @Override
    public boolean updateModelByMU(UpdateData<ChaosRolePermissionData> data) {
        ChaosRolePermission entity = (ChaosRolePermission) convertService.convertToMuModel(data.getData(), ChaosRolePermission.class);
        return update(entity, new UpdateWrapper<ChaosRolePermission>().eq(Table.MU, data.getMu()));
    }

    @Override
    public ChaosRolePermissionData selectByMU(MU data) {
        return (ChaosRolePermissionData) convertService.convertToDTO(getOne(new QueryWrapper<ChaosRolePermission>()
                .eq(Table.MU, data.getMu())), ChaosRolePermissionData.class);
    }

    @Override
    public List<ChaosRolePermissionData> selectByData(ChaosRolePermissionData data) {
        QueryWrapper<ChaosRolePermission> query = new QueryWrapper<ChaosRolePermission>();
        query.lambda()
                .eq(!StringUtils.isEmpty(data.getRoleMu()), ChaosRolePermission::getRoleMu, data.getRoleMu())
                .eq(!StringUtils.isEmpty(data.getPermissionMu()), ChaosRolePermission::getPermissionMu, data.getPermissionMu());
        query.orderByDesc(Table.ID);
        return convertService.convertToDTO(list(query), ChaosRolePermissionData.class);
    }

    @Override
    public PageList<ChaosRolePermissionData> selectByPage(PageQueryDto<ChaosRolePermissionData> pageData) {
        QueryWrapper query = new QueryWrapper();
        query.orderByDesc(Table.ID);
        return new PageList(page(new Page(pageData.getPageNum(), pageData.getPageSize()), query));
    }

    @Override
    public List<ChaosRolePermissionData> selectRolePermissionByData(ChaosRolePermissionData data) {
        return baseMapper.selectRolePermissionByData(data.getRoleMu());
    }

    @Override
    public List<String> selectPermissionMusByRoleMu(ChaosRolePermissionData data) {
        return baseMapper.selectPermissionMusByRoleMu(data.getRoleMu());
    }




}
