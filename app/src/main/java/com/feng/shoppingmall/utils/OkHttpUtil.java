package com.feng.shoppingmall.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.utils
 * @作者：FENG
 * @类名：Ok
 * @创建时间：2023/1/1114:18
 * @描述：
 **/
public class OkHttpUtil {
    public static void sendOkHttpRequest(String url, okhttp3.Callback callback) {
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.MILLISECONDS) //设置连接超时时间
                .build();
        //创建请求内容
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
