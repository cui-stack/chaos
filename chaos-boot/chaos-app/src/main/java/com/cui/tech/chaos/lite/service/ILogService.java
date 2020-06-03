package com.cui.tech.chaos.lite.service;

/**
 * @author G.G
 * @date 2020/4/27 14:59
 */
public interface ILogService {
    void log(String userMu, String ip, String uri, long time, String request, String response);
}
