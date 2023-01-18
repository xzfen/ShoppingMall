package com.feng.shoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feng.shoppingmall.R;
import com.feng.shoppingmall.home.bean.ResultBeanData;
import com.feng.shoppingmall.utils.Constants;

import java.util.List;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.home.adapter
 * @作者：FENG
 * @类名：HotAdapter
 * @创建时间：2023/1/1521:13
 * @描述：
 **/
public class HotAdapter extends BaseAdapter {
    private final Context mContext;
    private List<ResultBeanData.ResultBean.HotInfoBean> mHotInfoBeans;

    public HotAdapter(Context context, List<ResultBeanData.ResultBean.HotInfoBean> data) {
        this.mContext = context;
        this.mHotInfoBeans = data;
    }

    @Override
    public int getCount() {
        return mHotInfoBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mHotInfoBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //如果没有复用的View
        if (convertView == null) {
            //加载item的布局，得到布局对象
            convertView = View.inflate(mContext, R.layout.item_hot_grid_view, null);
            //创建内部ViewHolder并将布局item的view传入
            holder = new ViewHolder();
            holder.iv_hot=convertView.findViewById(R.id.iv_hot);
            holder.tv_name=convertView.findViewById(R.id.tv_name);
            holder.tv_price=convertView.findViewById(R.id.tv_price);
            //将ViewHolder存储在convertView中
            convertView.setTag(holder);
        }else{
            //重新获取ViewHolder
            holder= (ViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        ResultBeanData.ResultBean.HotInfoBean hotInfoBean = mHotInfoBeans.get(position);
        //设置数据
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE+hotInfoBean.getFigure()).into(holder.iv_hot);
        holder.tv_name.setText(hotInfoBean.getName());
        holder.tv_price.setText(hotInfoBean.getCoverPrice());
        return convertView;
    }

    static class ViewHolder {
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_price;
    }
}
