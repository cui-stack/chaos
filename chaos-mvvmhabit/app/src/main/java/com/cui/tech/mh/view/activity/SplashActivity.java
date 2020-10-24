package com.cui.tech.mh.view.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.lifecycle.ViewModelProviders;

import com.cui.tech.mh.BR;
import com.cui.tech.mh.R;
import com.cui.tech.mh.app.AppViewModelFactory;
import com.cui.tech.mh.databinding.ActivitySplashBinding;
import com.cui.tech.mh.vm.SplashViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @author G.G
 * @date 2020/8/11 23:06
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public SplashViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(SplashViewModel.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(LoginActivity.class);
                finish();
            }
        }, 1000);

    }

}
