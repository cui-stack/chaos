package com.cui.tech.okya.view.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.lifecycle.ViewModelProviders;

import com.cui.tech.okya.BR;
import com.cui.tech.okya.R;
import com.cui.tech.okya.app.AppViewModelFactory;
import com.cui.tech.okya.databinding.ActivitySplashBinding;
import com.cui.tech.okya.vm.SplashViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @author G.G
 * @date 2020/8/11 23:06
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {

    private Handler handler = new Handler();

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
        handler.postDelayed(runnableToLogin, 1000);

    }

    private Runnable runnableToLogin = new Runnable() {
        @Override
        public void run() {
            startActivity(LoginActivity.class);
            finish();
        }
    };


}
