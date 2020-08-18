package com.cui.tech.chaos.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.tech.chaos.manage.api.data.ChaosLinkData;
import com.cui.tech.chaos.manage.api.entity.ChaosLink;
import com.cui.tech.chaos.manage.api.service.IChaosLinkService;
import com.cui.tech.chaos.manage.service.mapper.ChaosLinkMapper;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.Table;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;
import com.cui.tech.chaos.model.service.ConvertService;
import com.cui.tech.chaos.model.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	@Autowired
	private PageService pageService;

	@Override
	public MU insertModel(ChaosLinkData data) {
		ChaosLink entity = (ChaosLink) convertService.convertToMuModel(data, ChaosLink.class);
		entity.setVersion(1);
		return save(entity) ? new MU(entity.getMu()) : null;
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
		//query.eq(!StringUtils.isEmpty(data.getPassword()),"password", data.getPassword());
		query.orderByDesc(Table.ID);
		return convertService.convertToDTO(list(query), ChaosLinkData.class);
	}

	@Override
	public PageList<ChaosLinkData> selectByPage(PageQueryDto<ChaosLinkData> pageData) {
		QueryWrapper query = new QueryWrapper();
		//query.eq(!StringUtils.isEmpty(pageData.getData().getPassword()),"phone", pageData.getData().getPhone());
		query.orderByDesc(Table.ID);
		return new PageList(page(pageService.page(pageData), query), ChaosLinkData.class);
	}

}
