package com.cui.tech.mh.model.datasource;

import com.cui.tech.mh.model.api.ApiService;
import com.cui.tech.mh.model.data.LoginData;
import com.cui.tech.mh.model.data.LoginUser;
import com.cui.tech.mh.model.data.UserData;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.base.DataResponse;
import me.goldze.mvvmhabit.base.MU;
import me.goldze.mvvmhabit.base.PageQuery;
import me.goldze.mvvmhabit.base.PageResponse;

public class HttpDataSource {
    private ApiService apiService;
    private volatile static HttpDataSource INSTANCE = null;

    public static HttpDataSource getInstance(ApiService apiService) {
        if (INSTANCE == null) {
            synchronized (HttpDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpDataSource(apiService);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private HttpDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<DataResponse<LoginUser>> login(String username, String password) {
        return apiService.login(new LoginData(username, password));
    }

    public Observable<DataResponse<UserData>> userInfo() {
        return apiService.userInfo();
    }

    public Observable<DataResponse<UserData>> userOne(String mu) {
        return apiService.userOne(new MU(mu));
    }

    public Observable<DataResponse<UserData>> userList(UserData data) {
        return apiService.userList(data);
    }

    public Observable<PageResponse<UserData>> userPage(PageQuery page, UserData data) {
        page.setData(data);
        return apiService.userPage(page);
    }

}
