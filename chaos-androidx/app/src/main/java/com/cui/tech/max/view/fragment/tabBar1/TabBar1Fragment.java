package com.cui.tech.max.view.fragment.tabBar1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.cui.tech.max.BR;
import com.cui.tech.max.R;
import com.cui.tech.max.fragment.FormFragment;
import com.cui.tech.max.fragment.HeadFragment;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * Created by goldze on 2018/7/18.
 */

public class TabBar1Fragment extends BaseFragment {
    Fragment headFragment;
    Fragment multiRecycleViewFragment;
    Fragment formFragment;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_tab_bar_1;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        headFragment = new HeadFragment();
        //multiRecycleViewFragment = new MultiRecycleViewFragment();
        formFragment = new FormFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.headFragment, headFragment);
        transaction.add(R.id.formFragment, formFragment);
        transaction.commit();
    }
}
