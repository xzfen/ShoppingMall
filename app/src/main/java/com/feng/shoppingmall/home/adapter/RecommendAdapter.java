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
 * @类名：RecommendAdapter
 * @创建时间：2023/1/1520:17
 * @描述：
 **/
public class RecommendAdapter extends BaseAdapter {
    private final Context mContext;
    List<ResultBeanData.ResultBean.RecommendInfoBean> mRecommendInfoBeans;

    public RecommendAdapter(Context context, List<ResultBeanData.ResultBean.RecommendInfoBean> data) {
        this.mContext=context;
        this.mRecommendInfoBeans = data;
    }

    @Override
    public int getCount() {
        return mRecommendInfoBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mRecommendInfoBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //如果没有复用的View
        if (convertView==null) {
            //加载item的布局，得到布局对象
            convertView = View.inflate(mContext, R.layout.item_recommend,null);
            //创建内部ViewHolder并将布局item的view传入
            holder = new ViewHolder();
            holder.iv_recommend = convertView.findViewById(R.id.iv_recommend);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            holder.tv_price = convertView.findViewById(R.id.tv_price);
            //将ViewHolder存储在convertView中
            convertView.setTag(holder);
        }else{
            //重新获取ViewHolder
            holder= (ViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = mRecommendInfoBeans.get(position);
        //设置数据
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE+recommendInfoBean.getFigure()).into(holder.iv_recommend);
        holder.tv_name.setText(recommendInfoBean.getName());
        holder.tv_price.setText(recommendInfoBean.getCoverPrice());
        return convertView;
    }

    static class ViewHolder{
        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;
    }
}
