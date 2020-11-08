package com.firepongo.chaos.web.service.login;

/**
 * @author G.G
 * @date 2020/5/19 17:43
 */
public interface ILoginKeyService {
    /**
     * 用户标识key规则
     * 1,多入口唯一登录 比如后台登录, 使用用户主键MU 作为redis_key,
     * 重新登录后失效先前颁发的token,达到唯一登录效果
     * 2,多端登录,原则上一个端只能有一个登录,不同的端可以同时登录
     * 使用用户MU+端标识作为redis_key
     * 3,小程序类登录 原则上 不需要挤出效果,各端相互独立
     * 使用 随机key作为 redis_key 比如 微信小程序 登录获取的session_key
     * 总
     * redis_key 通过加密办法(比如jwt) 返回前端,再次请求时添加在请求头
     * @param msg
     * @return
     */
    String key(String msg);

}
