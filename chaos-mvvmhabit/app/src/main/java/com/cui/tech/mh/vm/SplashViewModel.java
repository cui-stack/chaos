package com.cui.tech.mh.vm;

import android.app.Application;

import androidx.annotation.NonNull;

import com.cui.tech.mh.model.repository.Repository;

import me.goldze.mvvmhabit.base.BaseViewModel;


/**
 * @author G.G
 * @date 2020/8/11 23:07
 */
public class SplashViewModel extends BaseViewModel<Repository> {
    private static final String TAG = "SplashViewModel";

    public SplashViewModel(@NonNull Application application, Repository repository) {
        super(application, repository);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
