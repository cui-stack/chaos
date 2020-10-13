package com.cui.tech.chaos.model.db;

import lombok.Data;

/**
 * @author G.G
 * @date 2020/10/12 20:13
 */
@Data
public class SourceMu<T> extends MU {
    /**
     *请求来源
     */
    private String reqSource;
    private T data;
}
