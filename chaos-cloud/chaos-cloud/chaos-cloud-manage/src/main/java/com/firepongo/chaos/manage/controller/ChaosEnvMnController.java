package com.firepongo.chaos.manage.controller;

import com.firepongo.chaos.manage.api.data.ChaosEnvData;
import com.firepongo.chaos.manage.api.service.IChaosEnvService;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.app.result.DataResult;
import com.firepongo.chaos.app.result.PageResult;
import com.firepongo.chaos.web.annotation.ManageLoginToken;
import com.firepongo.chaos.web.base.BaseController;
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
        return dataResult(iChaosEnvService.insertModel(data));
    }

    @PostMapping("/update")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update( @ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosEnvData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosEnvService.updateModelByMU(data));
    }

    @PostMapping("/one")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosEnvData> one( @RequestBody MU data) throws Exception {
        return dataResult(iChaosEnvService.selectByMU(data));
    }

    @PostMapping("/list")
    @ManageLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosEnvData>> list( @RequestBody ChaosEnvData data) throws Exception {
        return dataResult(iChaosEnvService.selectByData(data));
    }

    @PostMapping("/page")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosEnvData> page( @RequestBody PageQueryDto<ChaosEnvData> data) throws Exception {
        return pageResult(iChaosEnvService.selectByPage(data));
    }

    @PostMapping("/delete")
    @ManageLoginToken
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete( @RequestBody MU data) throws Exception {
        return dataResult(iChaosEnvService.deleteModel(data));
    }

}
