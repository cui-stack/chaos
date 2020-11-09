package com.firepongo.chaos.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.firepongo.chaos.admin.api.data.ChaosLinkData;
import com.firepongo.chaos.admin.api.entity.ChaosLink;
import com.firepongo.chaos.admin.api.service.IChaosLinkService;
import com.firepongo.chaos.admin.service.mapper.ChaosLinkMapper;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.Table;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.app.service.ConvertService;
import com.firepongo.chaos.app.utils.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
* <p>
*  服务实现类
* </p>
*
* @author G.G
* @since 2020-08-17
*/
@Slf4j
@Service(interfaceClass = IChaosLinkService.class)
@Component
public class ChaosLinkServiceImpl extends ServiceImpl<ChaosLinkMapper, ChaosLink> implements IChaosLinkService {
	@Autowired
	private ConvertService convertService;

	@Override
	public MU insertModel(ChaosLinkData data) {
		ChaosLink entity = (ChaosLink) convertService.convertToMuModel(data, ChaosLink.class);
		entity.setVersion(1);
		return save(entity) ? MU.of(entity.getMu()) : null;
	}

	@Override
	public boolean deleteModel(MU data) {return removeById(data.getMu());}

	@Override
	public boolean updateModelByMU(UpdateData<ChaosLinkData> data) {
		ChaosLink entity = (ChaosLink) convertService.convertToMuModel(data.getData(), ChaosLink.class);
		return update(entity, new UpdateWrapper<ChaosLink>().eq(Table.MU, data.getMu()));
	}

	@Override
	public ChaosLinkData selectByMU(MU data) {
		return (ChaosLinkData) convertService.convertToDTO(getOne(new QueryWrapper<ChaosLink>()
			.eq(Table.MU, data.getMu())), ChaosLinkData.class);
	}

	@Override
	public List<ChaosLinkData> selectByData(ChaosLinkData data) {
		QueryWrapper<ChaosLink> query = new QueryWrapper<ChaosLink>();
		query.orderByAsc(Table.SORT);
		return convertService.convertToDTO(list(query), ChaosLinkData.class);
	}

	@Override
	public PageList<ChaosLinkData> selectByPage(PageQueryDto<ChaosLinkData> pageData) {
		QueryWrapper query = new QueryWrapper();
		query.eq(!StringUtils.isEmpty(pageData.getData().getStatus()),"status", pageData.getData().getStatus());
		query.orderByAsc(Table.SORT);
		return new PageList(page(PageHelper.page(pageData), query), ChaosLinkData.class);
	}

}
