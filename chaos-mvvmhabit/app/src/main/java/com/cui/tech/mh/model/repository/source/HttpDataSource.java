package com.cui.tech.mh.model.repository.source;


import com.cui.tech.mh.model.entity.LoginUser;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;

/**
 * Created by goldze on 2019/3/26.
 */
public interface HttpDataSource {
    Observable<BaseResponse<LoginUser>> login(String username, String password);

}
