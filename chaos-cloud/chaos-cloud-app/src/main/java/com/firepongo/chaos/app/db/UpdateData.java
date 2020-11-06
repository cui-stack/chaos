package com.firepongo.chaos.app.db;


import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author G.G
 * @date 2020/2/26 15:56
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UpdateData<T> extends MU {
    private T data;

    public UpdateData(String mu, T data) {
        this.setMu(mu);
        this.data = data;
    }
}
