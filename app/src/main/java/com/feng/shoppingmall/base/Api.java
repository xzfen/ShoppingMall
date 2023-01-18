package com.feng.shoppingmall.base;

import com.feng.shoppingmall.home.bean.ResultBeanData;
import com.feng.shoppingmall.utils.Constants;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.base
 * @作者：FENG
 * @类名：Api
 * @创建时间：2023/1/1116:43
 * @描述：
 **/
public interface Api {
    @GET("atguigu/json/HOME_URL.json")
    Call<ResponseBody> getHomeContents();
}
