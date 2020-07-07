package com.cui.tech.max.model.repository.source;


import com.cui.tech.max.model.entity.LoginEntity;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;

/**
 * Created by goldze on 2019/3/26.
 */
public interface HttpDataSource {
    Observable<BaseResponse<LoginEntity>> login(String userneme, String password);


}
