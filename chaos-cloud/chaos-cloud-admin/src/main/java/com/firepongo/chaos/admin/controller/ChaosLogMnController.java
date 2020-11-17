package com.firepongo.chaos.admin.controller;

import com.firepongo.chaos.app.db.ChaosLogData;
import com.firepongo.chaos.admin.api.entity.ChaosLog;
import com.firepongo.chaos.admin.api.service.IChaosLogService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author G.G
 * @since 2020-04-28
 */
@Api(tags = "ChaosLogMnController")
@RestController
@RequestMapping("/manage/chaos_log")
public class ChaosLogMnController extends BaseController {
    @Autowired
    private IChaosLogService iChaosLogService;

    @ManageLoginToken
    @PostMapping("/add")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ChaosLogData data, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosLogService.insertModel(data));
    }

    @ManageLoginToken
    @PostMapping("/one")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosLog> one(@RequestBody MU data) throws Exception {
        return dataResult(iChaosLogService.selectByMU(data));
    }

    @ManageLoginToken
    @PostMapping("/update")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update(@ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosLogData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosLogService.updateModelByMU(data));
    }

    @ManageLoginToken
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosLog>> list(@RequestBody ChaosLogData data) throws Exception {
        return dataResult(iChaosLogService.selectByData(data));
    }

    @ManageLoginToken
    @PostMapping("/page")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosLog> page(@RequestBody PageQueryDto<ChaosLogData> data) throws Exception {
        return pageResult(iChaosLogService.selectByPage(data));
    }

    @ManageLoginToken
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete(@RequestBody MU data) throws Exception {
        return dataResult(iChaosLogService.deleteModel(data));
    }

}
