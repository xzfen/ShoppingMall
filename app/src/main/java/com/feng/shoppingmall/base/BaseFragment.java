package com.feng.shoppingmall.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.base
 * @作者：FENG
 * @类名：BaseFragment
 * @创建时间：2023/1/711:57
 * @描述：：BaseFragment
 */
public abstract class BaseFragment extends Fragment {
    public Context mContext;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initView();
        initListener();
        initPresenter();
        initData();
        return view;
    }

    protected void initData() {
    }

    protected void initListener() {

    }

    /**
     * 初始化数据
     * 有子类实现，实现特有效果
     * @return
     */
    public abstract View initView();

    public void initPresenter() {

    }
}
