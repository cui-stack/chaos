package com.cui.tech.mh.model.datasource;


import com.cui.tech.mh.model.api.dto.LoginUser;

import me.goldze.mvvmhabit.base.DataResponse;

import io.reactivex.Observable;

/**
 * Created by goldze on 2019/3/26.
 */
public interface LoginHttpDataSource {
    Observable<DataResponse<LoginUser>> login(String username, String password);

}
