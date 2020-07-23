package com.cui.tech.mh.vm;

import android.app.Application;

import androidx.annotation.NonNull;

import com.cui.tech.mh.model.repository.Repository;

import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @author G.G
 * @date 2020/5/12 15:47
 */
public class MainViewModel extends BaseViewModel<Repository> {
    public MainViewModel(@NonNull Application application, Repository repository) {
        super(application, repository);
    }

}
