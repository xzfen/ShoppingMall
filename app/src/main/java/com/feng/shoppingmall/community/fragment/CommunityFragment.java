package com.feng.shoppingmall.community.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.feng.shoppingmall.base.BaseFragment;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.home.fragment
 * @作者：FENG
 * @类名：HomeFragment
 * @创建时间：2023/1/820:49
 * @描述：发现的Fragment
 **/
public class CommunityFragment extends BaseFragment {
    private static final String TAG = CommunityFragment.class.getSimpleName();
    private TextView textView;

    @Override
    public View initView() {
        Log.d(TAG,"发现页面的Fragment的UI被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        Log.d(TAG,"发现页面的Fragment的数据被初始化了");
        textView.setText("发现");
    }
}
