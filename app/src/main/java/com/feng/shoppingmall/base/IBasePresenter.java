package com.feng.shoppingmall.base;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.base
 * @作者：FENG
 * @类名：IBasePresenter
 * @创建时间：2023/1/1115:20
 * @描述：
 **/
public interface IBasePresenter<T> {
    /**
     * 注册UI通知接口
     *
     * @param callback
     */
    void registerViewCallback(T callback);

    /**
     * 取消UI通知的接口
     *
     * @param callback
     */
    void unregisterViewCallback(T callback);
}
