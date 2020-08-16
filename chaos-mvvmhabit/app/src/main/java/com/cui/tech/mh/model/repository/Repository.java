package com.cui.tech.okya.model.repository;


import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import me.goldze.mvvmhabit.base.DataResponse;
import me.goldze.mvvmhabit.base.PageQuery;
import me.goldze.mvvmhabit.base.PageResponse;
import com.cui.tech.okya.model.api.dto.data.OkyaUserData;
import com.cui.tech.okya.model.api.dto.LoginUser;
import com.cui.tech.okya.model.datasource.LoginHttpDataSource;
import com.cui.tech.okya.model.datasource.LoginLocalDataSource;
import com.cui.tech.okya.model.datasource.UserHttpDataSource;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.base.BaseModel;

public class Repository extends BaseModel implements LoginHttpDataSource, LoginLocalDataSource, UserHttpDataSource {
    private volatile static Repository INSTANCE = null;

    private final LoginHttpDataSource loginHttpDataSource;

    private final LoginLocalDataSource LoginLocalDataSource;

    private final UserHttpDataSource userHttpDataSource;

    private Repository(@NonNull LoginHttpDataSource loginHttpDataSource,
                       @NonNull LoginLocalDataSource loginLocalDataSource,
                       @NonNull UserHttpDataSource userHttpDataSource) {
        this.loginHttpDataSource = loginHttpDataSource;
        this.LoginLocalDataSource = loginLocalDataSource;
        this.userHttpDataSource = userHttpDataSource;

    }

    public static Repository getInstance(LoginHttpDataSource httpDataSource,
                                         LoginLocalDataSource localDataSource,
                                         UserHttpDataSource userHttpDataSource) {
        if (INSTANCE == null) {
            synchronized (Repository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Repository(httpDataSource, localDataSource,userHttpDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void saveUserName(String userName) {
        LoginLocalDataSource.saveUserName(userName);
    }

    @Override
    public String getUserName() {
        return LoginLocalDataSource.getUserName();
    }

    @Override
    public Observable<DataResponse<LoginUser>> login(String username, String password) {
        return loginHttpDataSource.login(username, password);
    }

    @Override
    public Observable<DataResponse<OkyaUserData>> one(String mu) {
        return userHttpDataSource.one(mu);
    }

    @Override
    public Observable<DataResponse<OkyaUserData>> list(OkyaUserData data) {
        return userHttpDataSource.list(data);
    }

    @Override
    public Observable<PageResponse<OkyaUserData>> page(PageQuery page, OkyaUserData data) {
        return userHttpDataSource.page(page, data);
    }
}
