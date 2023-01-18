package com.feng.shoppingmall.base;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.base
 * @作者：FENG
 * @类名：IBaseCallback
 * @创建时间：2023/1/1114:07
 * @描述：回调接口基类
 **/
public interface IBaseCallback {
    void onError();

    void onLoading();

    void onEmpty();
}
