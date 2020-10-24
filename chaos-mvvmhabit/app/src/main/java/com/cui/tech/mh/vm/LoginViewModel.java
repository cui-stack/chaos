package com.cui.tech.mh.vm;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.cui.tech.mh.model.data.LoginUser;
import com.cui.tech.mh.model.repository.Repository;
import com.cui.tech.mh.view.activity.MainActivity;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.base.DataResponse;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @author G.G
 * @date 2020/5/12 15:47
 */
public class LoginViewModel extends BaseViewModel<Repository> {
    private static final String TAG = "LoginViewModel";

    public LoginViewModel(@NonNull Application application, Repository loginRepository) {
        super(application, loginRepository);
    }

    public ObservableField<String> username = new ObservableField<>("");

    public ObservableField<String> password = new ObservableField<>("");

    public ObservableInt isShowTvUsernameClear = new ObservableInt();

    public BindingCommand clearUsername = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            username.set("");
        }
    });

    public BindingCommand<Boolean> onFocusUsername = new BindingCommand<>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean hasFocus) {
            isShowTvUsernameClear.set(hasFocus ? View.VISIBLE : View.INVISIBLE);
        }
    });

    public BindingCommand changeShowPassword = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.pSwitchEvent.setValue(uc.pSwitchEvent.getValue() == null || !uc.pSwitchEvent.getValue());
        }
    });

    public class UIChangeObservable {
        public SingleLiveEvent<Boolean> pSwitchEvent = new SingleLiveEvent<>();
    }

    public UIChangeObservable uc = new UIChangeObservable();


    public BindingCommand doLogin = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(username.get())) {
                ToastUtils.showShort("请输入账号！");
                return;
            }
            if (TextUtils.isEmpty(password.get())) {
                ToastUtils.showShort("请输入密码！");
                return;
            }
            login();
        }
    });

    private void login() {
        ToastUtils.showShort("开始登录");
        addSubscribe(model.login(username.get(), password.get())
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        //showDialog();
                    }
                })
                .subscribe(new Consumer<DataResponse<LoginUser>>() {
                    @Override
                    public void accept(DataResponse<LoginUser> data) throws Exception {
                        Log.d(TAG, "accept:  " + data.toString());
                        //dismissDialog();
                        if (data.isOk()) {
                            model.saveToken(data.getData().getToken());
                            ToastUtils.showShort("登录成功");
                            startActivity(MainActivity.class);
                            ToastUtils.showShort("进入主页");
                            finish();
                        } else {
                            ToastUtils.showShort(data.getMsg());
                        }

                    }
                }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
