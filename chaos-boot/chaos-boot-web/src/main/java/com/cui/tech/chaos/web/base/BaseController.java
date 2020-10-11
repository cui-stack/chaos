package com.cui.tech.chaos.web.base;


import com.cui.tech.chaos.model.DTO;
import com.cui.tech.chaos.model.db.DATA;
import com.cui.tech.chaos.model.exception.BusinessException;
import com.cui.tech.chaos.model.login.ManageLoginUser;
import com.cui.tech.chaos.model.login.WxMiniLoginUser;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.result.*;
import com.cui.tech.chaos.web.service.ManageLoginKeyService;
import com.cui.tech.chaos.web.service.WxminiLoginKeyService;
import com.cui.tech.chaos.web.service.helper.JWTHelper;
import com.cui.tech.chaos.web.service.helper.RedisHelper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * @author G.G
 * 基础控制类，所有的Controller都规范继承此类，一些公用的代码后续可以在此扩展
 * @version 1.0
 * @date 2018/10/12
 */
public abstract class BaseController<T> {
    @Autowired
    private RedisHelper redisHelper;
    @Autowired
    private JWTHelper jwtHelper;

    @Autowired
    private WxminiLoginKeyService wxminiLoginKeyService;
    @Autowired
    private ManageLoginKeyService manageLoginKeyService;

    public String getToken(HttpServletRequest request) {
        return request.getHeader("token");
    }

    public ManageLoginUser getMnLoginUser(HttpServletRequest request) {
        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return getMnLoginUserByToken(token);
    }

    public ManageLoginUser getMnLoginUserByToken(String token) {
        String key = manageLoginKeyService.key(jwtHelper.getUserMuInJwtData(token));
        return (ManageLoginUser) redisHelper.get(key);
    }

    public WxMiniLoginUser getWxLoginUser(HttpServletRequest request) {
        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return (WxMiniLoginUser) getWxLoginUserByToken(token);
    }

    public WxMiniLoginUser getWxLoginUserByToken(String token) {
        String key = wxminiLoginKeyService.key(token);
        return (WxMiniLoginUser) redisHelper.get(key);
    }

    public String getMnLoginRole(HttpServletRequest request) {
        ManageLoginUser user = getMnLoginUser(request);
        if (user == null) {
            return "";
        }
        return user.getRole().getName();

    }

    public String getMnLoginMU(HttpServletRequest request) {
        ManageLoginUser user = getMnLoginUser(request);
        if (user == null) {
            return "";
        }
        return user.getMu();
    }

    public String getWxLoginMU(HttpServletRequest request) {
        WxMiniLoginUser user = getWxLoginUser(request);
        if (user == null) {
            return "";
        }
        return user.getMu();
    }

    public Integer getWxLoginID(HttpServletRequest request) {
        WxMiniLoginUser user = getWxLoginUser(request);
        if (user == null) {
            return -1;
        }
        return user.getId();
    }

    public void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String msg = String.join(",", bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList()));
            throw new BusinessException(ResultEnum.VALIDATE.getCode(), msg);
        }
    }

    public DataResult getResult(T data) {
        DataResult dataResult = new DataResult();
        if (data instanceof Boolean) {
            if (!(Boolean) data) {
                dataResult.failure();
                dataResult.setData(data);
            }
        } else if (data instanceof DTO) {
            if (data == null) {
                dataResult.failure();
                dataResult.setData(new DTO());
            }
        }
        return dataResult;
    }


    public PageResult<T> getResult(PageList<T> pageList) {
        PageResult<T> listResult = new PageResult<T>();
        if (pageList == null) {
            listResult.failure();
            pageList = new PageList();
        }
        listResult.setPage(pageList);
        return listResult;
    }

    public MarkPageResult<T> getResult(PageList<T> pageList, String mark) {
        MarkPageResult<T> listResult = new MarkPageResult<T>();
        if (pageList == null) {
            listResult.failure();
            pageList = new PageList();
        }
        listResult.setPage(pageList);
        listResult.setMark(mark);
        return listResult;
    }

    public MarkPagesResult<T> getResult(PageList<T> pl1, PageList<T> pl2, String mark) {
        MarkPagesResult<T> mpr = new MarkPagesResult<T>();
        if (pl1 == null || pl2 == null) {
            mpr.failure();
            pl1 = new PageList();
            pl2 = new PageList();
        }
        mpr.setPages(Lists.newArrayList(pl1, pl2));
        mpr.setMark(mark);
        return mpr;
    }

}
