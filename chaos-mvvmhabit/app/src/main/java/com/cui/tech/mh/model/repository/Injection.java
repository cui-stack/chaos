package com.cui.tech.okya.model.repository;


import com.cui.tech.okya.model.api.service.LoginApiService;
import com.cui.tech.okya.model.api.service.UserApiService;
import com.cui.tech.okya.model.datasource.impl.LoginLocalDataSourceImpl;
import com.cui.tech.okya.model.datasource.LoginLocalDataSource;
import com.cui.tech.okya.model.datasource.LoginHttpDataSource;
import com.cui.tech.okya.model.datasource.impl.LoginHttpDataSourceImpl;
import com.cui.tech.okya.model.datasource.UserHttpDataSource;
import com.cui.tech.okya.model.datasource.impl.UserHttpDataSourceImpl;
import com.cui.tech.okya.utils.RetrofitClient;

/**
 * 注入全局的数据仓库，可以考虑使用Dagger2。（根据项目实际情况搭建，千万不要为了架构而架构）
 * Created by goldze on 2019/3/26.
 */
public class Injection {
    public static Repository provideRepository() {
        LoginApiService loginApiService = RetrofitClient.getInstance().create(LoginApiService.class);
        LoginHttpDataSource httpDataSource = LoginHttpDataSourceImpl.getInstance(loginApiService);

        LoginLocalDataSource loginLocalDataSourceSource = LoginLocalDataSourceImpl.getInstance();

        UserApiService userApiService = RetrofitClient.getInstance().create(UserApiService.class);
        UserHttpDataSource userHttpDataSource= UserHttpDataSourceImpl.getInstance(userApiService);
        return Repository.getInstance(httpDataSource, loginLocalDataSourceSource,userHttpDataSource);
    }
}
