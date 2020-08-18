package com.cui.tech.mh.model.api.service;


import me.goldze.mvvmhabit.base.DataResponse;

import com.cui.tech.mh.model.api.dto.LoginDto;
import com.cui.tech.mh.model.api.dto.LoginUser;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface LoginApiService {

    @POST("app/login")
    Observable<DataResponse<LoginUser>> login(@Body LoginDto loginDto);
}
