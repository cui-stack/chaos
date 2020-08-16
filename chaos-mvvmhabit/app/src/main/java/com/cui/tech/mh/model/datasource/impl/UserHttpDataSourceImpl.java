package com.cui.tech.okya.model.datasource.impl;


import me.goldze.mvvmhabit.base.DataResponse;
import me.goldze.mvvmhabit.base.MU;
import me.goldze.mvvmhabit.base.PageQuery;
import me.goldze.mvvmhabit.base.PageResponse;
import com.cui.tech.okya.model.api.dto.data.OkyaUserData;
import com.cui.tech.okya.model.api.service.UserApiService;
import com.cui.tech.okya.model.datasource.UserHttpDataSource;

import io.reactivex.Observable;

public class UserHttpDataSourceImpl implements UserHttpDataSource {
    private UserApiService apiService;
    private volatile static UserHttpDataSourceImpl INSTANCE = null;

    public static UserHttpDataSourceImpl getInstance(UserApiService apiService) {
        if (INSTANCE == null) {
            synchronized (UserHttpDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserHttpDataSourceImpl(apiService);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private UserHttpDataSourceImpl(UserApiService apiService) {
        this.apiService = apiService;
    }


    @Override
    public Observable<DataResponse<OkyaUserData>> one(String mu) {
        return apiService.one(new MU(mu));
    }

    @Override
    public Observable<DataResponse<OkyaUserData>> list(OkyaUserData data) {
        return apiService.list(data);
    }

    @Override
    public Observable<PageResponse<OkyaUserData>> page(PageQuery page, OkyaUserData data) {
        return apiService.page(page);
    }
}
