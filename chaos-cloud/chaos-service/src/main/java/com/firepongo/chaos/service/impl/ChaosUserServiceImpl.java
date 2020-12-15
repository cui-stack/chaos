package com.firepongo.chaos.service.impl;

import org.apache.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.firepongo.chaos.app.service.ConvertService;
import com.firepongo.chaos.app.utils.PageHelper;
import com.firepongo.chaos.api.entity.ChaosUser;
import com.firepongo.chaos.api.data.ChaosUserData;
import com.firepongo.chaos.api.service.IChaosUserService;
import com.firepongo.chaos.service.mapper.ChaosUserMapper;
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
* @since 2020-11-17
*/
@Slf4j
@Service(interfaceClass = IChaosUserService.class)
@Component
public class ChaosUserServiceImpl extends ServiceImpl<ChaosUserMapper, ChaosUser> implements IChaosUserService {
	@Autowired
	private ConvertService convertService;

	@Override
	public MU insertModel(ChaosUserData data) {
		ChaosUser entity = (ChaosUser) convertService.convertToMuModel(data, ChaosUser.class);
		entity.setVersion(1);
		return save(entity) ? MU.of(entity.getMu()) : null;
	}

	@Override
	public boolean deleteModel(MU data) {return removeById(data.getMu());}

	@Override
	public boolean updateModelByMU(UpdateData<ChaosUserData> data) {
		ChaosUser entity = (ChaosUser) convertService.convertToMuModel(data.getData(), ChaosUser.class);
		return update(entity, new UpdateWrapper<ChaosUser>().eq(Table.MU, data.getMu()));
	}

	@Override
	public ChaosUserData selectByMU(MU data) {
		return (ChaosUserData) convertService.convertToDTO(getOne(new QueryWrapper<ChaosUser>()
			.eq(Table.MU, data.getMu())), ChaosUserData.class);
	}

	@Override
	public List<ChaosUserData> selectByData(ChaosUserData data) {
		QueryWrapper<ChaosUser> query = new QueryWrapper<ChaosUser>();
		query.lambda().eq(!StringUtils.isEmpty(data.getMu()), ChaosUser::getMu, data.getMu());
		query.orderByDesc(Table.ID);
		return convertService.convertToDTO(list(query), ChaosUserData.class);
	}

	@Override
	public PageList<ChaosUserData> selectByPage(PageQueryDto<ChaosUserData> pageData) {
		QueryWrapper<ChaosUser> query = new QueryWrapper<ChaosUser>();
		query.lambda().eq(!StringUtils.isEmpty(pageData.getData().getMu()), ChaosUser::getMu, pageData.getData().getMu());
		query.orderByDesc(Table.ID);
		return new PageList(page(PageHelper.page(pageData), query), ChaosUserData.class);
	}

}
