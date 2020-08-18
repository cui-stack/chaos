package com.cui.tech.mh.model.datasource.impl;


import com.cui.tech.mh.model.api.dto.LoginDto;
import com.cui.tech.mh.model.api.dto.LoginUser;
import com.cui.tech.mh.model.api.service.LoginApiService;
import com.cui.tech.mh.model.datasource.LoginHttpDataSource;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.base.DataResponse;

/**
 * Created by goldze on 2019/3/26.
 */
public class LoginHttpDataSourceImpl implements LoginHttpDataSource {
    private LoginApiService apiService;
    private volatile static LoginHttpDataSourceImpl INSTANCE = null;

    public static LoginHttpDataSourceImpl getInstance(LoginApiService apiService) {
        if (INSTANCE == null) {
            synchronized (LoginHttpDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LoginHttpDataSourceImpl(apiService);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private LoginHttpDataSourceImpl(LoginApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<DataResponse<LoginUser>> login(String username, String password) {
        return apiService.login(new LoginDto(username, password));
    }
}
