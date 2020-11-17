package com.firepongo.chaos.web.base;


import com.firepongo.chaos.app.DTO;
import com.firepongo.chaos.app.exception.BusinessException;
import com.firepongo.chaos.app.login.app.AppLoginUser;
import com.firepongo.chaos.app.login.manage.ManageLoginUser;
import com.firepongo.chaos.app.login.wxmini.WxMiniLoginUser;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.app.result.page.MarkPageResult;
import com.firepongo.chaos.app.result.page.MarkPagesResult;
import com.firepongo.chaos.app.result.page.PageResult;
import com.firepongo.chaos.web.service.JwtService;
import com.firepongo.chaos.web.service.login.app.AppLoginKeyService;
import com.firepongo.chaos.web.service.login.manage.ManageLoginKeyService;
import com.firepongo.chaos.web.service.login.wxmini.WxminiLoginKeyService;
import com.firepongo.chaos.web.service.RedisService;
import com.firepongo.chaos.app.result.*;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

public abstract class BaseController<T> {
    @Autowired
    private RedisService redisHelper;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private WxminiLoginKeyService wxminiLoginKeyService;
    @Autowired
    private ManageLoginKeyService manageLoginKeyService;
    @Autowired
    private AppLoginKeyService appLoginKeyService;

    public String getToken(HttpServletRequest request) {
        return request.getHeader("token");
    }

    public ManageLoginUser getMnLoginUserByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new BusinessException();
        }
        String key = manageLoginKeyService.key(jwtService.getUserMuInJwtData(token));
        ManageLoginUser user = (ManageLoginUser) redisHelper.get(key);
        if (user == null) {
            throw new BusinessException();
        }
        return user;
    }

    public WxMiniLoginUser getWxLoginUserByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new BusinessException();
        }
        String key = wxminiLoginKeyService.key(token);
        WxMiniLoginUser user = (WxMiniLoginUser) redisHelper.get(key);
        if (user == null) {
            throw new BusinessException();
        }
        return user;
    }

    public AppLoginUser getAppLoginUserByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new BusinessException();
        }
        String key = appLoginKeyService.key(token);
        AppLoginUser user = (AppLoginUser) redisHelper.get(key);
        if (user == null) {
            throw new BusinessException();
        }
        return user;
    }


    public void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String msg = String.join(",", bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList()));
            throw new BusinessException(ResultEnum.VALIDATE.getCode(), msg);
        }
    }

    public DataResult dataResult(T data) {
        return dataResult(data, null);
    }

    public DataResult dataResult(T data, ResultMsg msg) {
        DataResult dataResult = new DataResult();
        if (data == null) {
            if (msg == null || StringUtils.isEmpty(msg.getFailure())) {
                dataResult.failure();
            } else {
                dataResult.msg(ResultEnum.FAILURE.getCode(), msg.getFailure());
            }
            dataResult.setData(null);
        } else {
            if (msg == null || StringUtils.isEmpty(msg.getSuccess())) {
                dataResult.success();
            } else {
                dataResult.msg(ResultEnum.SUCCESS.getCode(), msg.getSuccess());
            }
            dataResult.setData(data);
        }
        return dataResult;
    }

    public PageResult<T> pageResult(PageList<T> pageList) {
        PageResult<T> listResult = new PageResult<T>();
        if (pageList == null) {
            listResult.failure();
            listResult.setPage(new PageList());
        } else {
            listResult.success();
            listResult.setPage(pageList);
        }
        return listResult;
    }

    public MarkPageResult<T> pageResult(PageList<T> pageList, String mark) {
        MarkPageResult<T> listResult = new MarkPageResult<T>();
        if (pageList == null) {
            listResult.failure();
            listResult.setPage(new PageList());
        } else {
            listResult.success();
            listResult.setPage(pageList);
        }
        listResult.setPage(pageList);
        listResult.setMark(mark);
        return listResult;
    }

    public MarkPagesResult<T> pageResult(PageList<T> pl1, PageList<T> pl2, String mark) {
        MarkPagesResult<T> mpr = new MarkPagesResult<T>();
        if (pl1 == null || pl2 == null) {
            mpr.failure();
            mpr.setPages(Lists.newArrayList(new PageList(), new PageList()));
        } else {
            mpr.success();
            mpr.setPages(Lists.newArrayList(pl1, pl2));
        }
        mpr.setMark(mark);
        return mpr;
    }

}
