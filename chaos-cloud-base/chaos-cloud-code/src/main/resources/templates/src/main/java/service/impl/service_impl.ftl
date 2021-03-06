package ${cfg.p}.service.impl;

import org.apache.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.firepongo.chaos.app.service.ConvertService;
import com.firepongo.chaos.app.utils.PageHelper;
import ${cfg.p}.api.entity.${entity};
import ${cfg.p}.api.data.${entity}Data;
import ${cfg.p}.api.service.I${entity}Service;
import ${cfg.p}.service.mapper.${entity}Mapper;
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
* ${table.comment!} 服务实现类
* </p>
*
* @author ${author}
* @since ${date}
*/
@Slf4j
@Component
@Service(interfaceClass = ${table.serviceName}.class)
public class ${table.serviceImplName} extends ServiceImpl<${table.mapperName}, ${entity}> implements ${table.serviceName} {
	@Autowired
	private ConvertService convertService;

	@Override
	public MU insertModel(${entity}Data data) {
		${entity} entity = (${entity}) convertService.convertToMuModel(data, ${entity}.class);
		return save(entity) ? MU.of(entity.getMu()) : null;
	}

	@Override
	public boolean deleteModel(MU data) {return removeById(data.getMu());}

	@Override
	public boolean updateModelByMU(UpdateData<${entity}Data> data) {
		${entity} entity = (${entity}) convertService.convertToMuModel(data.getData(), ${entity}.class);
		return update(entity, new UpdateWrapper<${entity}>().eq(Table.MU, data.getMu()));
	}

	@Override
	public ${entity}Data selectByMU(MU data) {
		return (${entity}Data) convertService.convertToDTO(getOne(new QueryWrapper<${entity}>()
			.eq(Table.MU, data.getMu())), ${entity}Data.class);
	}

	@Override
	public List<${entity}Data> selectByData(${entity}Data data) {
		QueryWrapper<${entity}> query = new QueryWrapper<${entity}>();
		query.lambda().eq(!StringUtils.isEmpty(data.getMu()), ${entity}::getMu, data.getMu());
		query.orderByDesc(Table.ID);
		return convertService.convertToDTO(list(query), ${entity}Data.class);
	}

	@Override
	public PageList<${entity}Data> selectByPage(PageQueryDto<${entity}Data> pageData) {
		QueryWrapper<${entity}> query = new QueryWrapper<${entity}>();
		query.lambda().eq(!StringUtils.isEmpty(pageData.getData().getMu()), ${entity}::getMu, pageData.getData().getMu());
		query.orderByDesc(Table.ID);
		return new PageList(page(PageHelper.page(pageData), query), ${entity}Data.class);
	}

}
