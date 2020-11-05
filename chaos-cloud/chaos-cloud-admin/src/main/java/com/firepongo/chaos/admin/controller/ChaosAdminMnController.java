package com.firepongo.chaos.admin.controller;

import com.firepongo.chaos.admin.api.data.ChaosAdminData;
import com.firepongo.chaos.admin.api.entity.ChaosAdmin;
import com.firepongo.chaos.admin.api.entity.ChaosRole;
import com.firepongo.chaos.admin.api.service.IChaosAdminService;
import com.firepongo.chaos.admin.service.tran.AdminTranService;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.app.result.page.PageResult;
import com.firepongo.chaos.web.base.BaseController;
import com.firepongo.chaos.web.helper.IpUtil;
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
 * @since 2020-04-14
 */
@Api(tags = "ChaosAdminMnController")
@RestController
@RequestMapping("/manage/chaos_admin")
public class ChaosAdminMnController extends BaseController {

    @Autowired
    private IChaosAdminService iChaosAdminService;

    @Autowired
    private AdminTranService adminTranService;

    @PostMapping("/add")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ChaosAdminData data, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        validate(bindingResult);
        data.setIp(IpUtil.getIpAddr(request));
        return dataResult(adminTranService.addAdmin(data));
    }

    @PostMapping("/one")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosAdmin> one(@RequestBody MU data) throws Exception {
        return dataResult(iChaosAdminService.selectByMU(data));
    }

    @PostMapping("/update")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update(@ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosAdminData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosAdminService.updateModelByMU(data));
    }

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosAdmin>> list(@RequestBody ChaosAdminData data) throws Exception {
        return dataResult(iChaosAdminService.selectByData(data));
    }

    @PostMapping("/page")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosAdmin> page(@RequestBody PageQueryDto<ChaosAdminData> data) throws Exception {
        return pageResult(iChaosAdminService.selectByPage(data));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete(@RequestBody MU data) throws Exception {
        return dataResult(iChaosAdminService.deleteModel(data));
    }

    @PostMapping("/index")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosRole> index(HttpServletRequest request) throws Exception {
        return dataResult(iChaosAdminService.selectIndexLink(getMnLoginMU(request)));
    }

    @PostMapping("/adminRole")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosAdminData> adminRole(@RequestBody MU data) throws Exception {
        return dataResult(iChaosAdminService.selectAdminRoleByMU(data));
    }

    @PostMapping("/updateAdminRole")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> updateAdminRole(@ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosAdminData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(adminTranService.updateAdminRole(data));
    }

    @PostMapping("/updateByPassword")
    @ApiOperation(value = "更新用户", notes = "", httpMethod = "POST")
    public DataResult updateByPassword(
            @RequestBody @Validated UpdateData<ChaosAdminData> data, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        validate(bindingResult);
        boolean flag = iChaosAdminService.updateModelByPassword(data);
        if (!flag) {
            return dataResult("密码错误,更新失败!");
        }
        return dataResult(true);

    }


}
