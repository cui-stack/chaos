package com.firepongo.chaos.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.firepongo.chaos.admin.api.data.*;
import com.firepongo.chaos.admin.api.data.ChaosAdminRoleData;
import com.firepongo.chaos.admin.api.data.ChaosAdminRoleData;
import com.firepongo.chaos.admin.api.entity.ChaosAdminRole;
import com.firepongo.chaos.admin.api.entity.ChaosAdminRole;
import com.firepongo.chaos.admin.api.entity.ChaosAdminRole;
import com.firepongo.chaos.admin.api.entity.ChaosAdminRole;
import com.firepongo.chaos.admin.api.service.IChaosAdminRoleService;
import com.firepongo.chaos.admin.service.mapper.ChaosAdminRoleMapper;
import com.firepongo.chaos.admin.api.data.ChaosAdminRoleData;
import com.firepongo.chaos.admin.api.service.IChaosAdminRoleService;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.Table;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.app.service.ConvertService;
import com.firepongo.chaos.app.utils.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
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
@Component
@Service(interfaceClass = IChaosAdminRoleService.class)
public class ChaosAdminRoleServiceImpl extends ServiceImpl<ChaosAdminRoleMapper, ChaosAdminRole> implements IChaosAdminRoleService {
    @Autowired
    private ConvertService convertService;

    @Override
    public MU insertModel(ChaosAdminRoleData data) {
        ChaosAdminRole entity = (ChaosAdminRole) convertService.convertToMuModel(data, ChaosAdminRole.class);
        return save(entity) ? MU.of(entity.getMu()) : null;
    }

    @Override
    public boolean deleteModel(MU data) {
        return removeById(data.getMu());
    }

    @Override
    public boolean updateModelByMU(UpdateData<ChaosAdminRoleData> data) {
        ChaosAdminRole entity = (ChaosAdminRole) convertService.convertToMuModel(data.getData(), ChaosAdminRole.class);
        return update(entity, new UpdateWrapper<ChaosAdminRole>().eq(Table.MU, data.getMu()));
    }

    @Override
    public boolean updateRoleByAdmin(String adminMU, String roleMU) {
        ChaosAdminRole entity = new ChaosAdminRole();
        entity.setRoleMu(roleMU);
        return update(entity,
                new UpdateWrapper<ChaosAdminRole>().lambda()
                        .eq(ChaosAdminRole::getAdminMu, adminMU)
        );
    }

    @Override
    public ChaosAdminRoleData selectByMU(MU data) {
        return (ChaosAdminRoleData) convertService.convertToDTO(getOne(new QueryWrapper<ChaosAdminRole>()
                .eq(Table.MU, data.getMu())), ChaosAdminRoleData.class);
    }

    @Override
    public List<ChaosAdminRoleData> selectByData(ChaosAdminRoleData data) {
        QueryWrapper<ChaosAdminRole> query = new QueryWrapper<ChaosAdminRole>();
        query.lambda()
                .eq(!StringUtils.isEmpty(data.getAdminMu()), ChaosAdminRole::getAdminMu, data.getAdminMu())
                .eq(!StringUtils.isEmpty(data.getRoleMu()), ChaosAdminRole::getRoleMu, data.getRoleMu());
        query.orderByDesc(Table.ID);
        return convertService.convertToDTO(list(query), ChaosAdminRoleData.class);
    }

    @Override
    public PageList<ChaosAdminRoleData> selectByPage(PageQueryDto<ChaosAdminRoleData> pageData) {
        QueryWrapper query = new QueryWrapper();
        query.orderByDesc(Table.ID);
        return new PageList(page(PageHelper.page(pageData), query), ChaosAdminRoleData.class);

    }


    @Override
    public ChaosAdminRoleData selectByAdmin(String adminMU) {
        return (ChaosAdminRoleData) convertService.convertToDTO(getOne(new QueryWrapper<ChaosAdminRole>()
                .eq("admin_mu", adminMU)), ChaosAdminRoleData.class);
    }

}
