package com.cui.tech.mh.model.api.service;


import com.cui.tech.mh.model.api.dto.data.UserData;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.base.DataResponse;
import me.goldze.mvvmhabit.base.MU;
import me.goldze.mvvmhabit.base.PageQuery;
import me.goldze.mvvmhabit.base.PageResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface UserApiService {
    String url = "/api/user/";

    @POST(url + "one")
    Observable<DataResponse< UserData>> one(@Body MU mu);

    @POST(url + "list")
    Observable<DataResponse< UserData>> list(@Body UserData data);

    @POST(url + "page")
    Observable<PageResponse<UserData>> page(@Body PageQuery<UserData> pageQuery);
}
