package com.cui.tech.chaos.manage.controller;

import com.cui.tech.chaos.manage.api.data.ChaosEnvData;
import com.cui.tech.chaos.manage.api.service.IChaosEnvService;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageQueryDto;
import com.cui.tech.chaos.model.result.DataResult;
import com.cui.tech.chaos.model.result.PageResult;
import com.cui.tech.chaos.web.annotation.ManageLoginToken;
import com.cui.tech.chaos.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
*
* @author G.G
* @since 2020-08-18
*/
@Slf4j
@Api(tags = "ChaosEnvMnController")
@RestController
@RequestMapping("/manage/chaos_env")
public class ChaosEnvMnController extends BaseController {

    @Autowired
    private IChaosEnvService iChaosEnvService;

    @PostMapping("/add")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ChaosEnvData data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return getResult(iChaosEnvService.insertModel(data));
    }

    @PostMapping("/update")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update( @ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosEnvData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return getResult(iChaosEnvService.updateModelByMU(data));
    }

    @PostMapping("/one")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosEnvData> one( @RequestBody MU data) throws Exception {
        return getResult(iChaosEnvService.selectByMU(data));
    }

    @PostMapping("/list")
    @ManageLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosEnvData>> list( @RequestBody ChaosEnvData data) throws Exception {
        return getResult(iChaosEnvService.selectByData(data));
    }

    @PostMapping("/page")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosEnvData> page( @RequestBody PageQueryDto<ChaosEnvData> data) throws Exception {
        return getResult(iChaosEnvService.selectByPage(data));
    }

    @PostMapping("/delete")
    @ManageLoginToken
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete( @RequestBody MU data) throws Exception {
        return getResult(iChaosEnvService.deleteModel(data));
    }

}
