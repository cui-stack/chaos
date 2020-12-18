package com.firepongo.chaos.manager.controller;

import com.firepongo.chaos.api.data.ChaosInfoData;
import com.firepongo.chaos.api.service.IChaosInfoService;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.app.result.page.PageResult;
import com.firepongo.chaos.web.annotation.ManageLoginToken;
import com.firepongo.chaos.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
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
* @since 2020-12-18
*/
@Slf4j
@Api(tags = "ChaosInfoMnController")
@RestController
@RequestMapping("/manage/chaosInfo")
public class ChaosInfoMnController extends BaseController {

    @Reference
    private IChaosInfoService iChaosInfoService;

    @PostMapping("/add")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ChaosInfoData data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosInfoService.insertModel(data));
    }

    @PostMapping("/update")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update( @ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosInfoData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosInfoService.updateModelByMU(data));
    }

    @PostMapping("/one")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosInfoData> one( @RequestBody MU data) throws Exception {
        return dataResult(iChaosInfoService.selectByMU(data));
    }

    @PostMapping("/list")
    @ManageLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosInfoData>> list( @RequestBody ChaosInfoData data) throws Exception {
        return dataResult(iChaosInfoService.selectByData(data));
    }

    @PostMapping("/page")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosInfoData> page( @RequestBody PageQueryDto<ChaosInfoData> data) throws Exception {
        return pageResult(iChaosInfoService.selectByPage(data));
    }

    @PostMapping("/delete")
    @ManageLoginToken
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete( @RequestBody MU data) throws Exception {
        return dataResult(iChaosInfoService.deleteModel(data));
    }

}
