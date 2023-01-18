package com.feng.shoppingmall.home.callback;

import com.feng.shoppingmall.base.IBaseCallback;
import com.feng.shoppingmall.home.bean.ResultBeanData;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.home.callback
 * @作者：FENG
 * @类名：IHomeCallback
 * @创建时间：2023/1/1114:10
 * @描述：主页回调接口
 **/
public interface IHomeCallback extends IBaseCallback {
    void onHomeContentsLoaded(ResultBeanData resultBeanData);
}
