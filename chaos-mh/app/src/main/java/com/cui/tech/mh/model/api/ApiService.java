package com.cui.tech.mh.model.api;

import me.goldze.mvvmhabit.base.DataResponse;
import me.goldze.mvvmhabit.base.MU;
import me.goldze.mvvmhabit.base.PageQuery;
import me.goldze.mvvmhabit.base.PageResponse;

import com.cui.tech.mh.model.data.LoginData;
import com.cui.tech.mh.model.data.LoginUser;
import com.cui.tech.mh.model.data.UserData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiService {
    @POST("app/login")
    Observable<DataResponse<LoginUser>> login(@Body LoginData loginDto);

    String user_url = "/api/chaos_user/";

    @POST(user_url + "info")
    Observable<DataResponse<UserData>> userInfo();

    @POST(user_url + "one")
    Observable<DataResponse<UserData>> userOne(@Body MU mu);

    @POST(user_url + "list")
    Observable<DataResponse<UserData>> userList(@Body UserData data);

    @POST(user_url + "page")
    Observable<PageResponse<UserData>> userPage(@Body PageQuery<UserData> pageQuery);

}
