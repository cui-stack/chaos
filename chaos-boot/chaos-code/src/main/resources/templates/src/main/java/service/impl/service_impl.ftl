package ${cfg.p}.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.tech.chaos.service.ConvertService;
import com.cui.tech.chaos.service.PageService;
import ${cfg.p}.api.entity.${entity};
import ${cfg.p}.api.data.${entity}Data;
import ${cfg.p}.api.service.I${entity}Service;
import ${cfg.p}.service.mapper.${entity}Mapper;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.Table;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;
import org.springframework.beans.BeanUtils;
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
@Service(interfaceClass = ${table.serviceName}.class)
@Component
public class ${table.serviceImplName} extends ServiceImpl<${table.mapperName}, ${entity}> implements ${table.serviceName} {
	@Autowired
	private ConvertService convertService;
	@Autowired
	private PageService pageService;

	@Override
	public MU insertModel(${entity}Data data) {
		${entity} entity = (${entity}) convertService.convertToMuModel(data, ${entity}.class);
		entity.setVersion(1);
		return save(entity) ? new MU(entity.getMu()) : null;
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
		//query.eq(!StringUtils.isEmpty(data.getPassword()),"password", data.getPassword());
		query.orderByDesc(Table.ID);
		return convertService.convertToDTO(list(query), ${entity}Data.class);
	}

	@Override
	public PageList<${entity}Data> selectByPage(PageQueryDto<${entity}Data> pageData) {
		QueryWrapper query = new QueryWrapper();
		//query.eq(!StringUtils.isEmpty(pageData.getData().getPassword()),"phone", pageData.getData().getPhone());
		query.orderByDesc(Table.ID);
		return new PageList(page(pageService.page(pageData), query), ${entity}Data.class);
	}

}
