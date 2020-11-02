package ${cfg.p}.client.controller;

import ${cfg.p}.api.entity.${entity};
import ${cfg.p}.api.data.${entity}Data;
import ${cfg.p}.api.service.I${entity}Service;
import MU;
import UpdateData;
import PageQueryDto;
import DataResult;
import PageResult;
import com.cui.tech.chaos.web.annotation.WxminiLoginToken;
import com.cui.tech.chaos.web.base.BaseController;
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
@Api(tags = "${entity}Controller")
@RestController
@RequestMapping("/api/${table.entityPath}")
public class ${table.controllerName} extends ${superControllerClass} {
    @Autowired
    private I${entity}Service i${entity}Service;

    @PostMapping("/one")
    @WxminiLoginToken
    @ApiOperation(value = "单条", notes = "", httpMethod = "POST")
    public DataResult<${entity}Data> one(
        @RequestBody MU data) throws Exception {
    return dataResult(i${entity}Service.selectByMU(data));
    }

    @PostMapping("/list")
    @WxminiLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<${entity}Data>> list( @RequestBody ${entity}Data data) throws Exception {
         return dataResult(i${entity}Service.selectByData(data));
    }

    @PostMapping("/page")
    @WxminiLoginToken
    @ApiOperation(value = "分页", notes = "", httpMethod = "POST")
    public PageResult<${entity}Data> page( @RequestBody PageQueryDto<${entity}Data> data) throws Exception {
        return pageResult(i${entity}Service.selectByPage(data));
    }

}
