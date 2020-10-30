package com.cui.tech.chaos.manage.controller;

import com.cui.tech.chaos.manage.api.data.ChaosLogData;
import com.cui.tech.chaos.manage.api.entity.ChaosAdmin;
import com.cui.tech.chaos.manage.api.entity.ChaosLog;
import com.cui.tech.chaos.manage.api.service.IChaosLogService;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.manage.ChaosAdminGetDto;
import com.cui.tech.chaos.model.manage.ChaosLogAddDto;
import com.cui.tech.chaos.model.page.PageQueryDto;
import com.cui.tech.chaos.model.result.DataResult;
import com.cui.tech.chaos.model.result.PageResult;
import com.cui.tech.chaos.web.annotation.ManageLoginToken;
import com.cui.tech.chaos.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author G.G
 * @since 2020-04-28
 */
@Api(tags = "ChaosLogMnController")
@RestController
@RequestMapping("/manage/chaos_log")
public class ChaosLogMnController extends BaseController {
    @Value("${app.manage.token:}")
    private String token;
    @Autowired
    private IChaosLogService iChaosLogService;


    @PostMapping("/add")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ChaosLogAddDto data, BindingResult bindingResult) throws Exception {
        if (!data.getToken().equals(token)) {
            return dataResult(false);
        }
        validate(bindingResult);
        String userMu = null;
        if (data.getUri().startsWith("/api") || data.getUri().startsWith("/wxmini")) {
            userMu = data.getUserMuToken();
        } else if (data.getUri().startsWith("/manage")) {
            userMu = getMnLoginUserByToken(data.getUserMuToken()).getMu();
        }
        ChaosLogData cld = new ChaosLogData(userMu, data.getIp(), data.getUri(), data.getTime(), data.getRequest(), data.getResponse());
        return dataResult(iChaosLogService.insertModel(cld));
    }


    @PostMapping("/one")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosLog> one(@RequestBody MU data) throws Exception {
        return dataResult(iChaosLogService.selectByMU(data));
    }

    @PostMapping("/update")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update(@ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosLogData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosLogService.updateModelByMU(data));
    }

    @PostMapping("/list")
    @ManageLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosLog>> list(@RequestBody ChaosLogData data) throws Exception {
        return dataResult(iChaosLogService.selectByData(data));
    }

    @PostMapping("/page")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosLog> page(@RequestBody PageQueryDto<ChaosLogData> data) throws Exception {
        return pageResult(iChaosLogService.selectByPage(data));
    }

    @PostMapping("/delete")
    @ManageLoginToken
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete(@RequestBody MU data) throws Exception {
        return dataResult(iChaosLogService.deleteModel(data));
    }

}
