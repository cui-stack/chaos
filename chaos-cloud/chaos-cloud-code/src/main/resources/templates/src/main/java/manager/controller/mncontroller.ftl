package ${cfg.p}.manager.controller;

import ${cfg.p}.api.data.${entity}Data;
import ${cfg.p}.api.service.I${entity}Service;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.app.result.page.PageResult;
import com.firepongo.chaos.web.base.BaseController;
import com.firepongo.chaos.web.annotation.ManageLoginToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
*
* @author ${author}
* @since ${date}
*/
@Slf4j
@Api(tags = "${entity}MnController")
@RestController
@RequestMapping("/manage/${table.entityPath}")
public class ${entity}MnController extends ${superControllerClass} {

    @Autowired
    private I${entity}Service i${entity}Service;

    @PostMapping("/add")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ${entity}Data data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(i${entity}Service.insertModel(data));
    }

    @PostMapping("/update")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update( @ApiParam(value = "") @RequestBody @Validated UpdateData<${entity}Data> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(i${entity}Service.updateModelByMU(data));
    }

    @PostMapping("/one")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<${entity}Data> one( @RequestBody MU data) throws Exception {
        return dataResult(i${entity}Service.selectByMU(data));
    }

    @PostMapping("/list")
    @ManageLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<${entity}Data>> list( @RequestBody ${entity}Data data) throws Exception {
        return dataResult(i${entity}Service.selectByData(data));
    }

    @PostMapping("/page")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<${entity}Data> page( @RequestBody PageQueryDto<${entity}Data> data) throws Exception {
        return pageResult(i${entity}Service.selectByPage(data));
    }

    @PostMapping("/delete")
    @ManageLoginToken
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete( @RequestBody MU data) throws Exception {
        return dataResult(i${entity}Service.deleteModel(data));
    }

}
