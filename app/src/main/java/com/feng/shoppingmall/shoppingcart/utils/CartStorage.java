package com.feng.shoppingmall.shoppingcart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.feng.shoppingmall.app.MyApplication;
import com.feng.shoppingmall.home.bean.GoodsBean;
import com.feng.shoppingmall.utils.CacheUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.shoppingcart.utils
 * @作者：FENG
 * @类名：CartStorage
 * @创建时间：2023/1/248:11
 * @描述：
 **/
//懒汉式单例模式
public class CartStorage {
    public static final String JSON_CART = "json_cart";
    private final Context mContext;
    //性能优于HashMap
    private SparseArray<GoodsBean> datas;

    //1. 私有化类的构造器
    private CartStorage(Context context){
        this.mContext=context;
        //把之前存储的数据读取出来
        datas=new SparseArray<>(100);
        //把本地读取的数据加入到SparseArray中
        listToSparse();
    }

    //2. 声明当前类对象，没有初始化
    //4. 此对象也必须声明为static的
    private static CartStorage instance;
    //3. 声明public、static的返回当前类对象的方法
    //得到购物车实例
    public static CartStorage getInstance(){
        if(instance == null) {
            instance = new CartStorage(MyApplication.getContext());
        }
        return instance;
    }

    private void listToSparse() {
        List<GoodsBean> carts = getAllData();
        //放到sparseArry中
        if (carts != null && carts.size() > 0) {
            for (int i = 0; i < carts.size(); i++) {
                GoodsBean goodsBean = carts.get(i);
                datas.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
            }
        }
    }

    private List<GoodsBean> sparseToList() {
        List<GoodsBean> carts = new ArrayList<>();
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean shoppingCart = datas.valueAt(i);
                carts.add(shoppingCart);
            }
        }
        return carts;
    }

    //本地获取json数据，并且通过Gson解析成list列表数据
    public List<GoodsBean> getAllData() {
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        //从本地获取缓存数据
        String savaJson = CacheUtils.getString(mContext, JSON_CART);
        if (!TextUtils.isEmpty(savaJson)) {
            //把数据转换成list
            goodsBeanList = new Gson().fromJson(savaJson, new TypeToken<List<GoodsBean>>() {}.getType());
        }
        return goodsBeanList;
    }

    public void addData(GoodsBean cart) {
        //添加数据
        GoodsBean tempCart = datas.get(Integer.parseInt(cart.getProduct_id()));
        //如果当前数据已经存在，就修改number递增
        if (tempCart != null) {
            tempCart.setNumber(tempCart.getNumber() + cart.getNumber());
        } else {
            //否则新增一个数据
            tempCart = cart;
            tempCart.setNumber(1);
        }
        //同步数据到内存中
        datas.put(Integer.parseInt(tempCart.getProduct_id()), tempCart);
        //保存数据到本地
        commit();
    }

    //保存数据到本地
    private void commit() {
        //把parseArray转换成list
        List<GoodsBean> carts = sparseToList();
        //把转换成String
        String json = new Gson().toJson(carts);
        // 保存
        CacheUtils.putString(mContext, JSON_CART, json);
    }


    public void deleteData(GoodsBean cart) {
        //删除内存数据
        datas.delete(Integer.parseInt(cart.getProduct_id()));
        //保存数据
        commit();
    }

    public void updataData(GoodsBean cart) {
        //修改内存数据
        datas.put(Integer.parseInt(cart.getProduct_id()), cart);
        //保存数据
        commit();
    }

    /**
     * 根据key查找书籍
     * @param goods_bean
     * @return
     */
    public GoodsBean findData(GoodsBean goods_bean) {
        GoodsBean goodsBean = datas.get(Integer.parseInt(goods_bean.getProduct_id()));
        if(goodsBean != null){
            return goods_bean;
        }
        return null;
    }
}
