package me.goldze.mvvmhabit.http;

import io.reactivex.observers.DisposableObserver;
import me.goldze.mvvmhabit.base.AppManager;
import me.goldze.mvvmhabit.base.DataResponse;
import me.goldze.mvvmhabit.base.PageResponse;
import me.goldze.mvvmhabit.utils.KLog;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.goldze.mvvmhabit.utils.Utils;

/**
 * Created by goldze on 2017/5/10.
 * 统一的Code封装处理。该类仅供参考，实际业务逻辑, 根据需求来定义，
 */

public abstract class ApiDisposableObserver<T> extends DisposableObserver<T> {
    public abstract void onResult(T t);

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof ResponseThrowable) {
            ResponseThrowable rError = (ResponseThrowable) e;
            ToastUtils.showShort(rError.message);
            return;
        }
        ToastUtils.showShort("网络异常");
    }

    @Override
    public void onStart() {
        super.onStart();
        ToastUtils.showShort("http is start");
        if (!NetworkUtil.isNetworkAvailable(Utils.getContext())) {
            KLog.d("无网络，读取缓存数据");
            onComplete();
        }
    }

    @Override
    public void onNext(Object o) {
        BaseResponse baseResponse = (BaseResponse) o;
        switch (Integer.parseInt(baseResponse.getCode())) {
            //处理成功
            case CodeRule.CODE_200:
                //onResult(handleResponse(baseResponse));
                break;
            //续签成功
            case CodeRule.CODE_201:
                //TODO
                break;
            case CodeRule.CODE_401:
                ToastUtils.showShort("登录已过期，请重新登录");
                AppManager.getAppManager().finishAllActivity();
                break;
            case CodeRule.CODE_500:
                break;
            default:
                ToastUtils.showShort(baseResponse.getMsg());
                break;
        }
    }

    private T handleResponse(BaseResponse baseResponse) {
        if (baseResponse instanceof DataResponse) {
            DataResponse dataResponse = (DataResponse) baseResponse;
            return (T) dataResponse.getData();
        } else if (baseResponse instanceof PageResponse) {
            PageResponse pageResponse = (PageResponse) baseResponse;
            return (T) pageResponse.getPage();
        }
        return null;
    }

    public static final class CodeRule {
        static final int CODE_200 = 200;
        static final int CODE_201 = 201;
        static final int CODE_401 = 401;

        static final int CODE_500 = 500;
        //参数为空
        static final int CODE_503 = 503;
        //没有数据
        static final int CODE_502 = 502;
        //无效的Token
        static final int CODE_510 = 510;
        //未登录
        static final int CODE_530 = 530;
        //请求的操作异常终止：未知的页面类型
        static final int CODE_551 = 551;
    }
}
