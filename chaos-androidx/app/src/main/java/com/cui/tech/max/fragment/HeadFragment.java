package com.cui.tech.max.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.cui.tech.max.BR;
import com.cui.tech.max.R;
import com.cui.tech.max.databinding.FragmentHeadBinding;
import com.cui.tech.max.vm.HeadViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * @author G.G
 * @date 2020/5/18 22:35
 */
public class HeadFragment extends BaseFragment<FragmentHeadBinding, HeadViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_head;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

}
