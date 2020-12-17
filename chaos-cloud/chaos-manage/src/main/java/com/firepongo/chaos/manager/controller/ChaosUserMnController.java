package com.firepongo.chaos.manager.controller;

import com.firepongo.chaos.api.data.ChaosUserData;
import com.firepongo.chaos.api.service.IChaosUserService;
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
import org.apache.dubbo.config.annotation.Reference;
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
* @author G.G
* @since 2020-11-17
*/
@Slf4j
@Api(tags = "ChaosUserMnController")
@RestController
@RequestMapping("/manage/chaosUser")
public class ChaosUserMnController extends BaseController {

    @Reference
    private IChaosUserService iChaosUserService;

    @PostMapping("/add")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ChaosUserData data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosUserService.insertModel(data));
    }

    @PostMapping("/update")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update(@ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosUserData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosUserService.updateModelByMU(data));
    }

    @PostMapping("/one")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosUserData> one(@RequestBody MU data) throws Exception {
        return dataResult(iChaosUserService.selectByMU(data));
    }

    @PostMapping("/list")
    @ManageLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosUserData>> list(@RequestBody ChaosUserData data) throws Exception {
        return dataResult(iChaosUserService.selectByData(data));
    }

    @PostMapping("/page")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosUserData> page(@RequestBody PageQueryDto<ChaosUserData> data) throws Exception {
        return pageResult(iChaosUserService.selectByPage(data));
    }

    @PostMapping("/delete")
    @ManageLoginToken
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete( @RequestBody MU data) throws Exception {
        return dataResult(iChaosUserService.deleteModel(data));
    }

}
