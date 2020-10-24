package com.cui.tech.mh.app;

import com.cui.tech.mh.model.api.ApiService;
import com.cui.tech.mh.model.datasource.HttpDataSource;
import com.cui.tech.mh.model.datasource.LocalDataSource;
import com.cui.tech.mh.model.repository.Repository;
import com.cui.tech.mh.utils.RetrofitClient;

public class Injection {
    public static Repository provideRepository() {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        HttpDataSource httpDataSource = HttpDataSource.getInstance(apiService);
        LocalDataSource localDataSourceSource = LocalDataSource.getInstance();

        return Repository.getInstance(httpDataSource, localDataSourceSource);
    }
}
