package com.feng.shoppingmall.home.presenter;

import com.feng.shoppingmall.base.IBasePresenter;
import com.feng.shoppingmall.home.callback.IHomeCallback;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.home.presenter
 * @作者：FENG
 * @类名：IHomePresenter
 * @创建时间：2023/1/1115:22
 * @描述：
 **/
public interface IHomePresenter extends IBasePresenter<IHomeCallback> {
    //获取主页数据
    void getHomeContentData();
}
