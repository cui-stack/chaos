package com.cui.tech.mh.model.repository.source.http.service;


import com.cui.tech.mh.model.entity.LoginUser;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiService {

    @FormUrlEncoded
    @POST("app/login")
    Observable<BaseResponse<LoginUser>> login(@Field("username") String username, @Field("password") String password);
}
