package com.cui.tech.mh.vm;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;


import com.cui.tech.mh.model.repository.Repository;
import com.cui.tech.mh.view.activity.MainActivity;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
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

    public class UIChangeObservable {
        public SingleLiveEvent<Boolean> pSwitchEvent = new SingleLiveEvent<>();
    }

    public UIChangeObservable uc = new UIChangeObservable();

    public LoginViewModel(@NonNull Application application, Repository loginRepository) {
        super(application, loginRepository);
        //username.set(model.getUserName());
    }


    public ObservableField<String> phone = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");
    public ObservableInt isShowTvPhoneClear = new ObservableInt();

    public BindingCommand clearPhone = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            phone.set("");
        }
    });

    public BindingCommand<Boolean> onFocusPhone = new BindingCommand<>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean hasFocus) {
            isShowTvPhoneClear.set(hasFocus ? View.VISIBLE : View.INVISIBLE);
        }
    });

    public BindingCommand changeShowPassword = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.pSwitchEvent.setValue(uc.pSwitchEvent.getValue() == null || !uc.pSwitchEvent.getValue());
        }
    });

    public BindingCommand doLogin = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(phone.get())) {
                ToastUtils.showShort("请输入账号！");
                return;
            }
            if (TextUtils.isEmpty(password.get())) {
                ToastUtils.showShort("请输入密码！");
                return;
            }
            ToastUtils.showShort("开始登录");

            addSubscribe(model.login(phone.get(), password.get())
                    .compose(RxUtils.schedulersTransformer())
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) throws Exception {
                            //showDialog();
                        }
                    })
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Exception {
                            Log.d(TAG, "accept:  " + o.toString());
                            //dismissDialog();
                            model.saveUserName(phone.get());
                            ToastUtils.showShort("登录成功");
                            startActivity(MainActivity.class);
                            ToastUtils.showShort("进入主页");
                            finish();
                        }
                    }));
        }
    });


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
