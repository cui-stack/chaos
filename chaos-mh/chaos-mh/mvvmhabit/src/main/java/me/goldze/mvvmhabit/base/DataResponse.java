package me.goldze.mvvmhabit.base;

import me.goldze.mvvmhabit.http.BaseResponse;

/**
 * @author G.G
 * @date 2020/8/16 13:36
 */
public class DataResponse<T> extends BaseResponse {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
