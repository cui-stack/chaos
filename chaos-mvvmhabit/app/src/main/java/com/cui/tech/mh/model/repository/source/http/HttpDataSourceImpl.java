package com.cui.tech.mh.model.repository.source.http;


import com.cui.tech.mh.model.dto.LoginDto;
import com.cui.tech.mh.model.entity.LoginUser;
import com.cui.tech.mh.model.repository.source.HttpDataSource;
import com.cui.tech.mh.model.repository.source.http.service.ApiService;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;

/**
 * Created by goldze on 2019/3/26.
 */
public class HttpDataSourceImpl implements HttpDataSource {
    private ApiService apiService;
    private volatile static HttpDataSourceImpl INSTANCE = null;

    public static HttpDataSourceImpl getInstance(ApiService apiService) {
        if (INSTANCE == null) {
            synchronized (HttpDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpDataSourceImpl(apiService);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private HttpDataSourceImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<BaseResponse<LoginUser>> login(String username, String password) {
        return apiService.login(new LoginDto(username, password));
    }
}
