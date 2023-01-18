package com.feng.shoppingmall.type.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.feng.shoppingmall.base.BaseFragment;
import com.feng.shoppingmall.home.fragment.HomeFragment;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.type.fragment
 * @作者：FENG
 * @类名：TypeFragment
 * @创建时间：2023/1/820:54
 * @描述：分类的Fragment
 **/
public class TypeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private TextView textView;

    @Override
    public View initView() {
        Log.d(TAG,"分类页面的Fragment的UI被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        Log.d(TAG,"分类页面的Fragment的数据被初始化了");
        textView.setText("分类");
    }
}
