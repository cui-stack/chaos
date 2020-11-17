package ${cfg.p}.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ${cfg.p}.api.entity.${entity};
import ${cfg.p}.api.data.${entity}Data;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;

import java.util.List;

/**
 *
 * @author ${author}
 * @since ${date}
*/
public interface ${table.serviceName} extends IService<${entity}> {

    MU insertModel(${entity}Data data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<${entity}Data> data);

    ${entity}Data selectByMU(MU data);

    List<${entity}Data> selectByData(${entity}Data data);

    PageList<${entity}Data> selectByPage(PageQueryDto<${entity}Data> pageData);
}
