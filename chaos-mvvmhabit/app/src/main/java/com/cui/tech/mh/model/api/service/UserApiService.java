package com.cui.tech.okya.model.api.service;


import me.goldze.mvvmhabit.base.DataResponse;
import me.goldze.mvvmhabit.base.MU;
import me.goldze.mvvmhabit.base.PageQuery;
import me.goldze.mvvmhabit.base.PageResponse;
import com.cui.tech.okya.model.api.dto.data.OkyaUserData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface UserApiService {
    String url = "/api/user/";

    @POST(url + "one")
    Observable<DataResponse<OkyaUserData>> one(@Body MU mu);

    @POST(url + "list")
    Observable<DataResponse<OkyaUserData>> list(@Body OkyaUserData data);

    @POST(url + "page")
    Observable<PageResponse<OkyaUserData>> page(@Body PageQuery<OkyaUserData> pageQuery);
}
