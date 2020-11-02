package com.firepongo.chaos.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.firepongo.chaos.manage.api.data.ChaosEnvData;
import com.firepongo.chaos.manage.api.entity.ChaosEnv;
import com.firepongo.chaos.manage.api.service.IChaosEnvService;
import com.firepongo.chaos.manage.service.mapper.ChaosEnvMapper;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.Table;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.app.service.ConvertService;
import com.firepongo.chaos.app.service.PageService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
*
* @author G.G
* @since 2020-08-18
*/
@Slf4j
@Service(interfaceClass = IChaosEnvService.class)
@Component
public class ChaosEnvServiceImpl extends ServiceImpl<ChaosEnvMapper, ChaosEnv> implements IChaosEnvService {
	@Autowired
	private ConvertService convertService;
	@Autowired
	private PageService pageService;

	@Override
	public MU insertModel(ChaosEnvData data) {
		ChaosEnv entity = (ChaosEnv) convertService.convertToMuModel(data, ChaosEnv.class);
		entity.setVersion(1);
		return save(entity) ? new MU(entity.getMu()) : null;
	}

	@Override
	public boolean deleteModel(MU data) {return removeById(data.getMu());}

	@Override
	public boolean updateModelByMU(UpdateData<ChaosEnvData> data) {
		ChaosEnv entity = (ChaosEnv) convertService.convertToMuModel(data.getData(), ChaosEnv.class);
		return update(entity, new UpdateWrapper<ChaosEnv>().eq(Table.MU, data.getMu()));
	}

	@Override
	public ChaosEnvData selectByMU(MU data) {
		return (ChaosEnvData) convertService.convertToDTO(getOne(new QueryWrapper<ChaosEnv>()
			.eq(Table.MU, data.getMu())), ChaosEnvData.class);
	}

	@Override
	public List<ChaosEnvData> selectByData(ChaosEnvData data) {
		QueryWrapper<ChaosEnv> query = new QueryWrapper<ChaosEnv>();
		//query.eq(!StringUtils.isEmpty(data.getPassword()),"password", data.getPassword());
		query.orderByDesc(Table.ID);
		return convertService.convertToDTO(list(query), ChaosEnvData.class);
	}

	@Override
	public PageList<ChaosEnvData> selectByPage(PageQueryDto<ChaosEnvData> pageData) {
		QueryWrapper query = new QueryWrapper();
		//query.eq(!StringUtils.isEmpty(pageData.getData().getPassword()),"phone", pageData.getData().getPhone());
		query.orderByDesc(Table.ID);
		return new PageList(page(pageService.page(pageData), query), ChaosEnvData.class);
	}

}
