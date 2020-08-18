package com.cui.tech.mh.view.activity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.cui.tech.mh.BR;
import com.cui.tech.mh.R;
import com.cui.tech.mh.app.AppViewModelFactory;
import com.cui.tech.mh.databinding.ActivityLoginBinding;
import com.cui.tech.mh.vm.LoginViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @author G.G
 * @date 2020/5/12 22:10
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public LoginViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(LoginViewModel.class);
    }

    @Override
    public void initViewObservable() {
        viewModel.uc.pSwitchEvent.observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean aBoolean) {
                if (viewModel.uc.pSwitchEvent.getValue()) {
                    //binding.tvPasswordVisible.setBackgroundResource(R.drawable.ic_password_visible);
                    binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //binding.tvPasswordVisible.setBackgroundResource(R.drawable.ic_password_invisible);
                    binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }
}
