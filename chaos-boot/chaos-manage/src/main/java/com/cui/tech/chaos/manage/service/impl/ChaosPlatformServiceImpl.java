package com.cui.tech.chaos.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.tech.chaos.manage.api.data.ChaosPlatformData;
import com.cui.tech.chaos.manage.api.entity.ChaosPlatform;
import com.cui.tech.chaos.manage.service.mapper.ChaosPlatformMapper;
import com.cui.tech.chaos.manage.api.service.IChaosPlatformService;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.Table;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import java.util.List;

/**
* <p>
*  服务实现类
* </p>
*
* @author G.G
* @since 2020-04-23
*/
@Service
@Component
public class ChaosPlatformServiceImpl extends ServiceImpl<ChaosPlatformMapper, ChaosPlatform> implements IChaosPlatformService {

	@Override
	public MU insertModel(ChaosPlatformData data) {
		ChaosPlatform entity = new ChaosPlatform();
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
	public boolean updateModelByMU(UpdateData<ChaosPlatformData> data) {
		ChaosPlatform entity = new ChaosPlatform();
		BeanUtils.copyProperties(data.getData(), entity);
		return update(entity,
				new UpdateWrapper<ChaosPlatform>()
						.eq(Table.MU, data.getMu())
		);
	}

	@Override
	public ChaosPlatform selectByMU(MU data) {
		return getOne(new QueryWrapper<ChaosPlatform>()
				.eq(Table.MU, data.getMu()));
	}

	@Override
	public List<ChaosPlatform> selectByData(ChaosPlatformData data) {
		QueryWrapper<ChaosPlatform> query = new QueryWrapper<ChaosPlatform>();
		query.orderByAsc(Table.SORT);
		return list(query);
	}

	@Override
	public PageList<ChaosPlatform> selectByPage(PageQueryDto<ChaosPlatformData> pageData) {
		QueryWrapper query = new QueryWrapper();
		query.eq(!StringUtils.isEmpty(pageData.getData().getName()),"name", pageData.getData().getName());
		query.orderByDesc(Table.ID);
		return new PageList(page(new Page(pageData.getPageNum(), pageData.getPageSize()), query));
	}

}
