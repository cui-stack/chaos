package com.cui.tech.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.tech.manage.api.data.ChaosAdminData;
import com.cui.tech.manage.api.data.ChaosAdminRoleData;
import com.cui.tech.manage.api.data.ChaosRolePermissionListData;
import com.cui.tech.manage.api.entity.ChaosRolePermission;
import com.cui.tech.manage.api.data.ChaosRolePermissionData;
import com.cui.tech.manage.api.service.IChaosRolePermissionService;
import com.cui.tech.manage.service.mapper.ChaosRolePermissionMapper;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.Table;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

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
@Service
@Component
public class ChaosRolePermissionServiceImpl extends ServiceImpl<ChaosRolePermissionMapper, ChaosRolePermission> implements IChaosRolePermissionService {

    @Override
    public MU insertModel(ChaosRolePermissionData data) {
        ChaosRolePermission entity = new ChaosRolePermission();
        BeanUtils.copyProperties(data, entity);
        boolean flag = save(entity);
        if (flag) {
            return new MU(entity.getMu());
        }
        return null;
    }

    @Override
    public boolean deleteModel(MU data) {
        return removeById(data.getMu());
    }

    @Override
    public boolean updateModelByMU(UpdateData<ChaosRolePermissionData> data) {
        ChaosRolePermission entity = new ChaosRolePermission();
        BeanUtils.copyProperties(data.getData(), entity);
        return update(entity,
                new UpdateWrapper<ChaosRolePermission>()
                        .eq(Table.MU, data.getMu())
        );
    }

    @Override
    public ChaosRolePermission selectByMU(MU data) {
        return getOne(new QueryWrapper<ChaosRolePermission>()
                .eq(Table.MU, data.getMu()));
    }

    @Override
    public List<ChaosRolePermission> selectByData(ChaosRolePermissionData data) {
        QueryWrapper<ChaosRolePermission> query = new QueryWrapper<ChaosRolePermission>();
        //query.eq(!data.getPassword().isEmpty(),"password", data.getPassword());
        query.orderByDesc(Table.ID);
        return list(query);
    }

    @Override
    public PageList<ChaosRolePermission> selectByPage(PageQueryDto<ChaosRolePermissionData> pageData) {
        QueryWrapper query = new QueryWrapper();
        //query.eq(!data.getPassword().isEmpty(),"phone", pageData.getData().getPhone());
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


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean grant(ChaosRolePermissionListData data) {
        try {
            ArrayList<ChaosRolePermission> addList = (ArrayList) data.getAddMus().stream().map(permissionMu -> {
                return new ChaosRolePermission(data.getRoleMu(), permissionMu);
            }).collect(Collectors.toList());
            boolean result = true;
            if (!CollectionUtils.isEmpty(addList)) {
                result = saveBatch(addList);
                if (!result) {
                    return false;
                }
            }
            if (!CollectionUtils.isEmpty(data.getDeleteMus())) {
                result = remove(new QueryWrapper<ChaosRolePermission>().in("permission_mu", data.getDeleteMus()));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("业务回滚", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

}
