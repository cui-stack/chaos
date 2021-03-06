package com.firepongo.chaos.admin.controller;

import com.firepongo.chaos.admin.api.data.ChaosLinkData;
import com.firepongo.chaos.admin.api.service.IChaosLinkService;
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
 * @author G.G
 * @since 2020-08-17
 */
@Slf4j
@Api(tags = "ChaosLinkMnController")
@RestController
@RequestMapping("/manage/chaos_link")
public class ChaosLinkMnController extends BaseController {

    @Autowired
    private IChaosLinkService iChaosLinkService;

    @ManageLoginToken
    @PostMapping("/add")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ChaosLinkData data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosLinkService.insertModel(data));
    }

    @ManageLoginToken
    @PostMapping("/update")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update(@ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosLinkData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosLinkService.updateModelByMU(data));
    }

    @ManageLoginToken
    @PostMapping("/one")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosLinkData> one(@RequestBody MU data) throws Exception {
        return dataResult(iChaosLinkService.selectByMU(data));
    }

    @ManageLoginToken
    @PostMapping("/list")
    @ApiOperation(value = "??????", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosLinkData>> list(@RequestBody ChaosLinkData data) throws Exception {
        return dataResult(iChaosLinkService.selectByData(data));
    }

    @ManageLoginToken
    @PostMapping("/page")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosLinkData> page(@RequestBody PageQueryDto<ChaosLinkData> data) throws Exception {
        return pageResult(iChaosLinkService.selectByPage(data));
    }

    @ManageLoginToken
    @PostMapping("/delete")
    @ApiOperation(value = "??????", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete(@RequestBody MU data) throws Exception {
        return dataResult(iChaosLinkService.deleteModel(data));
    }

}
