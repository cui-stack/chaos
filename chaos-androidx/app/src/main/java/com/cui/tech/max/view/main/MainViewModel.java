package com.cui.tech.max.view.main;

import android.app.Application;

import androidx.annotation.NonNull;

import com.cui.tech.max.repository.DemoRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @author G.G
 * @date 2020/5/12 15:47
 */
public class MainViewModel extends BaseViewModel<DemoRepository> {
    public MainViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
    }

}
