package com.cui.tech.okya.view.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.cui.tech.okya.BR;
import com.cui.tech.okya.R;
import com.cui.tech.okya.app.AppViewModelFactory;
import com.cui.tech.okya.databinding.ActivityMainBinding;
import com.cui.tech.okya.vm.MainViewModel;

import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @author G.G
 * @date 2020/5/18 17:40
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    private List<Fragment> mFragments;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public MainViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }

    @Override
    public void initViewObservable() {
    }

    @Override
    public void initData() {
    }


}
