package com.feng.shoppingmall.home.presenter;

import android.util.Log;

import com.feng.shoppingmall.base.Api;
import com.feng.shoppingmall.home.bean.ResultBeanData;
import com.feng.shoppingmall.home.callback.IHomeCallback;
import com.feng.shoppingmall.utils.Constants;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.home.presenter
 * @作者：FENG
 * @类名：HomePresenterImpl
 * @创建时间：2023/1/1115:24
 * @描述：
 **/
public class HomePresenterImpl implements IHomePresenter{
    private static final String TAG = "HomePresenterImpl";
    private IHomeCallback mCallback = null;

    @Override
    public void getHomeContentData() {
        //创建Retrofit对象并请求任务
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl(Constants.HOME_URL)
                .baseUrl(Constants.RETROFIT_BASE_URL)
                .build();
        Api api = retrofit.create(Api.class);
        retrofit2.Call<ResponseBody> task = api.getHomeContents();
        task.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                int code = response.code();
                Log.d(TAG, "onResponse: " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    try {
                        String data = response.body().string();
                        Log.d(TAG, "onResponse: " + data);
                        //解析数据
                        //使用google原生的gson解析json格式的数据
                        Gson gson = new Gson();
                        ResultBeanData resultBeanData = gson.fromJson(data, ResultBeanData.class);
                        //用fastjson解析json格式的数据
                        //ResultBeanData resultBeanData = JSON.parseObject(data,ResultBeanData.class);
                        mCallback.onHomeContentsLoaded(resultBeanData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
        //创建OkHttpClient对象并请求任务
        /*OkHttpUtil.sendOkHttpRequest(Constants.HOME_URL, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "onFailure: " + e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                int code = response.code();
                Log.d(TAG, "onResponse: " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    String data = response.body().string();
                    Log.d(TAG, "onResponse: " + data);
                    //解析数据
                    //使用google原生的gson解析json格式的数据
                    Gson gson = new Gson();
                    ResultBeanData resultBeanData = gson.fromJson(data, ResultBeanData.class);
                    //用fastjson解析json格式的数据
                    //ResultBeanData resultBeanData = JSON.parseObject(data,ResultBeanData.class);
                    mCallback.onHomeContentsLoaded(resultBeanData);

                }
            }
        });*/
    }

    @Override
    public void registerViewCallback(IHomeCallback callback) {
        this.mCallback=callback;
    }

    @Override
    public void unregisterViewCallback(IHomeCallback callback) {
        mCallback=null;
    }
}
