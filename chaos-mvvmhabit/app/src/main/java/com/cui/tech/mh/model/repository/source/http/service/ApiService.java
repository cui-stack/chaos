package com.cui.tech.mh.model.repository.source.http.service;


import com.cui.tech.mh.model.dto.LoginDto;
import com.cui.tech.mh.model.entity.LoginUser;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiService {

    @POST("app/login")
    Observable<BaseResponse<LoginUser>> login(@Body LoginDto loginDto);
}
