package com.firepongo.chaos.service.impl;

import org.apache.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.firepongo.chaos.app.service.ConvertService;
import com.firepongo.chaos.app.utils.PageHelper;
import com.firepongo.chaos.api.entity.ChaosInfo;
import com.firepongo.chaos.api.data.ChaosInfoData;
import com.firepongo.chaos.api.service.IChaosInfoService;
import com.firepongo.chaos.service.mapper.ChaosInfoMapper;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.Table;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
*
* @author G.G
* @since 2020-12-18
*/
@Slf4j
@Service(interfaceClass = IChaosInfoService.class)
@Component
public class ChaosInfoServiceImpl extends ServiceImpl<ChaosInfoMapper, ChaosInfo> implements IChaosInfoService {
	@Autowired
	private ConvertService convertService;

	@Override
	public MU insertModel(ChaosInfoData data) {
		ChaosInfo entity = (ChaosInfo) convertService.convertToMuModel(data, ChaosInfo.class);
		entity.setVersion(1);
		return save(entity) ? MU.of(entity.getMu()) : null;
	}

	@Override
	public boolean deleteModel(MU data) {return removeById(data.getMu());}

	@Override
	public boolean updateModelByMU(UpdateData<ChaosInfoData> data) {
		ChaosInfo entity = (ChaosInfo) convertService.convertToMuModel(data.getData(), ChaosInfo.class);
		return update(entity, new UpdateWrapper<ChaosInfo>().eq(Table.MU, data.getMu()));
	}

	@Override
	public ChaosInfoData selectByMU(MU data) {
		return (ChaosInfoData) convertService.convertToDTO(getOne(new QueryWrapper<ChaosInfo>()
			.eq(Table.MU, data.getMu())), ChaosInfoData.class);
	}

	@Override
	public List<ChaosInfoData> selectByData(ChaosInfoData data) {
		QueryWrapper<ChaosInfo> query = new QueryWrapper<ChaosInfo>();
		query.lambda().eq(!StringUtils.isEmpty(data.getMu()), ChaosInfo::getMu, data.getMu());
		query.orderByDesc(Table.ID);
		return convertService.convertToDTO(list(query), ChaosInfoData.class);
	}

	@Override
	public PageList<ChaosInfoData> selectByPage(PageQueryDto<ChaosInfoData> pageData) {
		QueryWrapper<ChaosInfo> query = new QueryWrapper<ChaosInfo>();
		query.lambda().eq(!StringUtils.isEmpty(pageData.getData().getMu()), ChaosInfo::getMu, pageData.getData().getMu());
		query.orderByDesc(Table.ID);
		return new PageList(page(PageHelper.page(pageData), query), ChaosInfoData.class);
	}

}
