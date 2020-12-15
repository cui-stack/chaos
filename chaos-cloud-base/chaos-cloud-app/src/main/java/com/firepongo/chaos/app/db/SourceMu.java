package com.firepongo.chaos.app.db;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author G.G
 * @date 2020/10/12 20:13
 */
@Data
@NoArgsConstructor
public class SourceMu<T> extends MU {
    /**
     * 请求来源
     */
    private String reqSource;
    private T data;

    public SourceMu(String mu, String reqSource, T data) {
        this.setMu(mu);
        this.reqSource = reqSource;
        this.data = data;
    }
}
