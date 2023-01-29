package com.feng.shoppingmall.home.bean;

import java.io.Serializable;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.home.bean
 * @作者：FENG
 * @类名：GoodsBean
 * @创建时间：2023/1/1621:05
 * @描述：
 **/
public class GoodsBean implements Serializable {
    private String cover_price;
    private String figure;
    private String name;
    private String product_id;
    /**
     *  数量
     * */
    private int number = 1;

    /**
     * 是否处于编辑状态
     */
    private boolean isEditing;
    /**
     * 是否被选中
     */
    private boolean isChildSelected=true;

    public String getCover_price() {
        return cover_price;
    }

    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setEditing(boolean editing) {
        isEditing = editing;
    }

    public boolean isChildSelected() {
        return isChildSelected;
    }

    public void setChildSelected(boolean childSelected) {
        isChildSelected = childSelected;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "cover_price='" + cover_price + '\'' +
                ", figure='" + figure + '\'' +
                ", name='" + name + '\'' +
                ", product_id='" + product_id + '\'' +
                ", number=" + number +
                ", isEditing=" + isEditing +
                ", isChildSelected=" + isChildSelected +
                '}';
    }
}
