package com.feng.shoppingmall.app;

import android.app.Application;
import android.content.Context;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.app
 * @作者：FENG
 * @类名：MyApplication
 * @创建时间：2023/1/1210:44
 * @描述：
 **/
public class MyApplication extends Application {
    private static Context mContext;
    public static Context getContext(){
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
    }
}
