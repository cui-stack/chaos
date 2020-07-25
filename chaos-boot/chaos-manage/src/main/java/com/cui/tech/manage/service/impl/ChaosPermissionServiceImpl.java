package com.cui.tech.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.tech.chaos.helper.PageHelper;
import com.cui.tech.chaos.model.Menu;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.Table;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;
import com.cui.tech.manage.api.data.ChaosPermissionData;
import com.cui.tech.manage.api.entity.ChaosPermission;
import com.cui.tech.manage.api.service.IChaosPermissionService;
import com.cui.tech.manage.service.mapper.ChaosPermissionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class ChaosPermissionServiceImpl extends ServiceImpl<ChaosPermissionMapper, ChaosPermission> implements IChaosPermissionService {

    @Autowired
    private PageHelper pageHelper;

    @Override
    public MU insertModel(ChaosPermissionData data) {
        ChaosPermission entity = new ChaosPermission();
        BeanUtils.copyProperties(data, entity);
        entity.setVersion(1);
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
    public boolean updateModelByMU(UpdateData<ChaosPermissionData> data) {
        ChaosPermission entity = new ChaosPermission();
        BeanUtils.copyProperties(data.getData(), entity);
        return update(entity,
                new UpdateWrapper<ChaosPermission>()
                        .eq(Table.MU, data.getMu())
        );
    }

    @Override
    public ChaosPermission selectByMU(MU data) {
        return getOne(new QueryWrapper<ChaosPermission>()
                .eq(Table.MU, data.getMu()));
    }

    @Override
    public List<ChaosPermission> selectByData(ChaosPermissionData data) {
        QueryWrapper<ChaosPermission> query = new QueryWrapper<ChaosPermission>();
        query.eq(!StringUtils.isEmpty(data.getPlatformMu()), "platform_mu", data.getPlatformMu());
        query.eq(!StringUtils.isEmpty(data.getIsroot()), "isroot", data.getIsroot());
        query.orderByDesc(Table.ID);
        return list(query);
    }

    @Override
    public PageList<ChaosPermission> selectByPage(PageQueryDto<ChaosPermissionData> pageData) {
        QueryWrapper query = new QueryWrapper();
        query.eq(!StringUtils.isEmpty(pageData.getData().getPlatformMu()), "platform_mu", pageData.getData().getPlatformMu());
        query.eq(!StringUtils.isEmpty(pageData.getData().getTitle()), "title", pageData.getData().getTitle());
        query.orderByDesc(Table.ID);
        return new PageList(page(new Page(pageData.getPageNum(), pageData.getPageSize()), query));
    }

    @Override
    public List<Menu> selectPermissionByAdmin(String adminmu) {
        List<Menu> menus = new ArrayList<>();
        List<ChaosPermissionData> ps = baseMapper.selectPermissionByAdmin(adminmu);
        if (ps == null || ps.isEmpty()) {
            return menus;
        }
        Map<String, List<ChaosPermissionData>> map = ps.stream().collect(Collectors.groupingBy(ChaosPermissionData::getSuptitle));
        for (String title : map.keySet()) {
            String icon = "";
            List<Menu> submenus = new ArrayList<>();
            for (ChaosPermissionData ip : map.get(title)) {
                if (StringUtils.isEmpty(icon)) {
                    icon = ip.getIcon();
                }
                submenus.add(new Menu(ip.getTitle(), ip.getLink()));
            }
            menus.add(new Menu(title, icon, submenus));
        }
        return menus;
    }

    @Override
    public PageList<ChaosPermission> selectBySortPage(PageQueryDto<ChaosPermissionData> pageData) {
        QueryWrapper query = new QueryWrapper();
        query.orderByDesc(Table.ID);
        return new PageList(page(pageHelper.sortPage(pageData), query));
    }

}
