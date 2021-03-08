package com.firepongo.chaos.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.firepongo.chaos.admin.api.data.ChaosResourceData;
import com.firepongo.chaos.admin.api.entity.ChaosResource;
import com.firepongo.chaos.admin.service.mapper.ChaosResourceMapper;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.Table;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.login.manage.ManageMenu;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.admin.api.service.IChaosResourceService;
import com.firepongo.chaos.app.service.ConvertService;
import com.firepongo.chaos.app.utils.PageHelper;
import org.apache.dubbo.config.annotation.Service;
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
@Service(interfaceClass = IChaosResourceService.class)
@Component
public class ChaosResourceServiceImpl extends ServiceImpl<ChaosResourceMapper, ChaosResource> implements IChaosResourceService {
    @Autowired
    private ConvertService convertService;

    @Override
    public MU insertModel(ChaosResourceData data) {
        ChaosResource entity = (ChaosResource) convertService.convertToMuModel(data, ChaosResource.class);
        return save(entity) ? MU.of(entity.getMu()) : null;
    }

    @Override
    public boolean deleteModel(MU data) {
        return removeById(data.getMu());
    }

    @Override
    public boolean updateModelByMU(UpdateData<ChaosResourceData> data) {
        ChaosResource entity = (ChaosResource) convertService.convertToMuModel(data.getData(), ChaosResource.class);
        return update(entity, new UpdateWrapper<ChaosResource>().eq(Table.MU, data.getMu()));
    }

    @Override
    public ChaosResourceData selectByMU(MU data) {
        return (ChaosResourceData) convertService.convertToDTO(getOne(new QueryWrapper<ChaosResource>()
                .eq(Table.MU, data.getMu())), ChaosResourceData.class);
    }

    @Override
    public List<ChaosResourceData> selectByData(ChaosResourceData data) {
        QueryWrapper<ChaosResource> query = new QueryWrapper<ChaosResource>();
        query.lambda()
                .eq(!StringUtils.isEmpty(data.getPlatformMu()), ChaosResource::getPlatformMu, data.getPlatformMu())
                .eq(!StringUtils.isEmpty(data.getIsRoot()), ChaosResource::getIsRoot, data.getIsRoot());
        query.orderByDesc(Table.ID);
        return convertService.convertToDTO(list(query), ChaosResourceData.class);

    }

    @Override
    public PageList<ChaosResourceData> selectByPage(PageQueryDto<ChaosResourceData> pageData) {
        QueryWrapper<ChaosResource> query = new QueryWrapper();
        query.lambda()
                .eq(!StringUtils.isEmpty(pageData.getData().getPlatformMu()), ChaosResource::getPlatformMu, pageData.getData().getPlatformMu())
                .eq(!StringUtils.isEmpty(pageData.getData().getTitle()), ChaosResource::getTitle, pageData.getData().getTitle());
        query.orderByDesc(Table.ID);
        return new PageList(page(new Page(pageData.getPageNum(), pageData.getPageSize()), query));
    }

    @Override
    public List<ManageMenu> selectResourceByAdmin(String adminMu) {
        List<ManageMenu> menus = new ArrayList<>();
        List<ChaosResourceData> ps = baseMapper.selectResourceByAdmin(adminMu);
        if (ps == null || ps.isEmpty()) {
            return menus;
        }
        Map<String, List<ChaosResourceData>> map = ps.stream().collect(Collectors.groupingBy(ChaosResourceData::getSuptitle));
        for (String title : map.keySet()) {
            String icon = "";
            List<ManageMenu> submenus = new ArrayList<>();
            for (ChaosResourceData ip : map.get(title)) {
                if (StringUtils.isEmpty(icon)) {
                    icon = ip.getIcon();
                }
                ManageMenu m = ManageMenu.of(ip.getTitle());
                submenus.add(m.setLink(ip.getLink()));
            }
            ManageMenu m = ManageMenu.of(title);
            menus.add(m.setIcon(icon).setSubmenus(submenus));
        }
        return menus;
    }

    @Override
    public PageList<ChaosResourceData> selectBySortPage(PageQueryDto<ChaosResourceData> pageData) {
        QueryWrapper query = new QueryWrapper();
        query.orderByDesc(Table.ID);
        return new PageList(page(PageHelper.page(pageData), query));
    }

}
