package com.cui.tech.mh.model.datasource;


import com.cui.tech.mh.model.api.dto.data.UserData;

import me.goldze.mvvmhabit.base.DataResponse;
import me.goldze.mvvmhabit.base.PageQuery;
import me.goldze.mvvmhabit.base.PageResponse;

import io.reactivex.Observable;

public interface UserHttpDataSource {
    Observable<DataResponse<UserData>> one(String mu);

    Observable<DataResponse<UserData>> list(UserData data);

    Observable<PageResponse<UserData>> page(PageQuery page, UserData data);

}
