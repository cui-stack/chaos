package com.cui.tech.max.model.repository.source.http.service;


import com.cui.tech.max.model.entity.LoginUser;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiService {

    @FormUrlEncoded
    @POST("api/login")
    Observable<BaseResponse<LoginUser>> login(@Field("phone") String phone);
}
