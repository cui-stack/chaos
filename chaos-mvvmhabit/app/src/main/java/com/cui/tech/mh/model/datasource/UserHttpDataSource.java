package com.cui.tech.okya.model.datasource;


import me.goldze.mvvmhabit.base.DataResponse;
import me.goldze.mvvmhabit.base.PageQuery;
import me.goldze.mvvmhabit.base.PageResponse;
import com.cui.tech.okya.model.api.dto.data.OkyaUserData;

import io.reactivex.Observable;

public interface UserHttpDataSource {
    Observable<DataResponse<OkyaUserData>> one(String mu);

    Observable<DataResponse<OkyaUserData>> list(OkyaUserData data);

    Observable<PageResponse<OkyaUserData>> page(PageQuery page, OkyaUserData data);

}
