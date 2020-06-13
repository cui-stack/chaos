package com.cui.tech.manage.manager.controller;

import com.cui.tech.chaos.lite.service.ILoginService;
import com.cui.tech.manage.api.entity.ChaosAdmin;
import com.cui.tech.manage.api.data.ChaosAdminData;
import com.cui.tech.manage.api.entity.ChaosRole;
import com.cui.tech.manage.api.service.IChaosAdminService;
import com.cui.tech.manage.service.tran.AdminTranService;
import com.cui.tech.chaos.model.Constants;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageQueryDto;
import com.cui.tech.chaos.model.result.DataResult;
import com.cui.tech.chaos.model.result.PageResult;
import com.cui.tech.chaos.util.addr.IpUtil;
import com.cui.tech.chaos.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cui.tech.chaos.annotation.ManageLoginToken;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author G.G
 * @since 2020-04-14
 */
@Api(tags = "ChaosAdminMnController")
@RestController
@RequestMapping("/manage/chaos_admin")
public class ChaosAdminMnController extends BaseController {
    @Qualifier("mnLoginService")
    @Autowired
    ILoginService mnLoginService;

    @Autowired
    private IChaosAdminService iChaosAdminService;

    @Autowired
    private AdminTranService adminTranService;

    @PostMapping("/add")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ChaosAdminData data, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        validate(bindingResult);
        data.setIp(IpUtil.getIpAddr(request));
        return getResult(adminTranService.addAdmin(data));
    }

    @PostMapping("/one")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosAdmin> one(@RequestBody MU data) throws Exception {
        return getResult(iChaosAdminService.selectByMU(data));
    }

    @PostMapping("/update")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update(@ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosAdminData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return getResult(iChaosAdminService.updateModelByMU(data));
    }

    @PostMapping("/list")
    @ManageLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosAdmin>> list(@RequestBody ChaosAdminData data) throws Exception {
        return getResult(iChaosAdminService.selectByData(data));
    }

    @PostMapping("/page")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosAdmin> page(@RequestBody PageQueryDto<ChaosAdminData> data) throws Exception {
        return getResult(iChaosAdminService.selectByPage(data));
    }

    @PostMapping("/delete")
    @ManageLoginToken
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete(@RequestBody MU data) throws Exception {
        return getResult(iChaosAdminService.deleteModel(data));
    }

    @PostMapping("/index")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosRole> index(HttpServletRequest request) throws Exception {
        return getResult(iChaosAdminService.selectIndexLink(getMnLoginMU(request)));
    }

    @PostMapping("/adminRole")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosAdminData> adminRole(@RequestBody MU data) throws Exception {
        return getResult(iChaosAdminService.selectAdminRoleByMU(data));
    }

    @PostMapping("/updateAdminRole")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> updateAdminRole(@ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosAdminData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return getResult(adminTranService.updateAdminRole(data));
    }

    @PostMapping("/updateByPassword")
    @ManageLoginToken
    @ApiOperation(value = "更新用户", notes = "", httpMethod = "POST")
    public DataResult updateByPassword(
            @RequestBody @Validated UpdateData<ChaosAdminData> data, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        validate(bindingResult);
        boolean flag = iChaosAdminService.updateModelByPassword(data);
        if (!flag) {
            return getResult("密码错误,更新失败!");
        }
        mnLoginService.doLogout(getToken(request));
        return getResult(true);

    }


}
