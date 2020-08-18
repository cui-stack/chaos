package com.cui.tech.mh.model.datasource.impl;


import com.cui.tech.mh.model.api.dto.data.UserData;
import com.cui.tech.mh.model.api.service.UserApiService;
import com.cui.tech.mh.model.datasource.UserHttpDataSource;

import me.goldze.mvvmhabit.base.DataResponse;
import me.goldze.mvvmhabit.base.MU;
import me.goldze.mvvmhabit.base.PageQuery;
import me.goldze.mvvmhabit.base.PageResponse;


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
    public Observable<DataResponse<UserData>> one(String mu) {
        return apiService.one(new MU(mu));
    }

    @Override
    public Observable<DataResponse<UserData>> list(UserData data) {
        return apiService.list(data);
    }

    @Override
    public Observable<PageResponse<UserData>> page(PageQuery page, UserData data) {
        return apiService.page(page);
    }
}
