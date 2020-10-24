package com.cui.tech.mh.model.repository;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import me.goldze.mvvmhabit.base.DataResponse;
import me.goldze.mvvmhabit.base.PageQuery;
import me.goldze.mvvmhabit.base.PageResponse;

import com.cui.tech.mh.constant.SPField;
import com.cui.tech.mh.model.data.LoginUser;
import com.cui.tech.mh.model.data.UserData;
import com.cui.tech.mh.model.datasource.HttpDataSource;
import com.cui.tech.mh.model.datasource.LocalDataSource;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.base.BaseModel;

public class Repository extends BaseModel {
    private volatile static Repository INSTANCE = null;

    private final HttpDataSource httpDataSource;

    private final LocalDataSource localDataSource;

    private Repository(@NonNull HttpDataSource httpDataSource,
                       @NonNull LocalDataSource localDataSource) {
        this.httpDataSource = httpDataSource;
        this.localDataSource = localDataSource;

    }

    public static Repository getInstance(HttpDataSource httpDataSource,
                                         LocalDataSource localDataSource) {
        if (INSTANCE == null) {
            synchronized (Repository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Repository(httpDataSource, localDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    public void saveUsername(String username) {
        localDataSource.save(SPField.USERNAME, username);
    }

    public void saveToken(String token) {
        localDataSource.save(SPField.TOKEN, token);
    }

    public String getToken() {
        return localDataSource.get(SPField.TOKEN);
    }

    public String getUserName() {
        return localDataSource.get(SPField.USERNAME);
    }

    public Observable<DataResponse<LoginUser>> login(String username, String password) {
        return httpDataSource.login(username, password);
    }

    public Observable<DataResponse<UserData>> userInfo() {
        return httpDataSource.userInfo();
    }

    public Observable<DataResponse<UserData>> userOne(String mu) {
        return httpDataSource.userOne(mu);
    }

    public Observable<DataResponse<UserData>> userList(UserData data) {
        return httpDataSource.userList(data);
    }

    public Observable<PageResponse<UserData>> userPage(PageQuery page, UserData data) {
        return httpDataSource.userPage(page, data);
    }


}
