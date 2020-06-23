package com.cui.tech.max.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.cui.tech.max.BR;
import com.cui.tech.max.R;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * Created by goldze on 2018/7/18.
 */

public class TabBar3Fragment extends BaseFragment {
    Fragment headFragment;
    Fragment viewPagerGroupFragment;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_tab_bar_3;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        headFragment = new HeadFragment();
        viewPagerGroupFragment=new ViewPagerGroupFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.headFragment, headFragment);
        transaction.add(R.id.viewPagerGroupFragment, viewPagerGroupFragment);
        transaction.commit();
    }

}
