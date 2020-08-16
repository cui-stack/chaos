package com.cui.tech.okya.model.datasource;


import me.goldze.mvvmhabit.base.DataResponse;
import com.cui.tech.okya.model.api.dto.LoginUser;

import io.reactivex.Observable;

/**
 * Created by goldze on 2019/3/26.
 */
public interface LoginHttpDataSource {
    Observable<DataResponse<LoginUser>> login(String username, String password);

}
