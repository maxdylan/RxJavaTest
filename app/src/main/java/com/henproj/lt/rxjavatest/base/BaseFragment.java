package com.henproj.lt.rxjavatest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Tong on 2018/4/26.
 */

public abstract class BaseFragment extends Fragment {
    protected abstract void initView();
    protected abstract int getLayoutId();

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        if (getLayoutId() != 0) {
            return inflater.inflate(getLayoutId(), container,false);
        }else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
