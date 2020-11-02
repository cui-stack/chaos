package com.firepongo.chaos.app.db;


import lombok.Data;

/**
 * @author G.G
 * @date 2020/2/26 15:56
 */
@Data
public class UpdateData<T> extends MU {
    private T data;

    public UpdateData() {
    }

    public UpdateData(String mu, T data) {
        super(mu);
        this.data = data;
    }
}
