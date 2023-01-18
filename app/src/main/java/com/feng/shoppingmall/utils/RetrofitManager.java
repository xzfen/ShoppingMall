package com.feng.shoppingmall.utils;

import retrofit2.Retrofit;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.utils
 * @作者：FENG
 * @类名：RetrofitManager
 * @创建时间：2023/1/1116:24
 * @描述：
 **/
public class RetrofitManager {
    private static final RetrofitManager ourInstance = new RetrofitManager();
    private final Retrofit mRetrofit;

    public static RetrofitManager getInstance() {
        return ourInstance;
    }

    private RetrofitManager() {
        //创建retrofit
        mRetrofit = new Retrofit.Builder()
                .build();
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
