package com.feng.shoppingmall.home.adapter;

import android.content.Context;
import android.util.Log;
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
 * @类名：ChannelAdapter
 * @创建时间：2023/1/1214:44
 * @描述：频道适配器
 **/
public class ChannelAdapter extends BaseAdapter {
    private static final String TAG = ChannelAdapter.class.getSimpleName();
    private List<ResultBeanData.ResultBean.ChannelInfoBean> mChannelInfoBeans;
    private Context mContext;

    public ChannelAdapter(Context context, List<ResultBeanData.ResultBean.ChannelInfoBean> data) {
        this.mContext = context;
        this.mChannelInfoBeans = data;
    }

    @Override
    public int getCount() {
        return mChannelInfoBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mChannelInfoBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 返回指定下标所对应的item的View对象
     * @param position 下标
     * @param convertView 可复用的缓存Item视图对象，前n+1为null
     * @param parent GridView对象
     * */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Log.d(TAG, "getView: position"+position+"convertView="+convertView);
        //如果没有复用的View
        if (convertView==null) {
            //加载item的布局，得到布局对象
            convertView=View.inflate(mContext, R.layout.item_channel,null);
            holder = new ViewHolder();
            holder.iv_icon = convertView.findViewById(R.id.iv_channel);
            holder.tv_title = convertView.findViewById(R.id.tv_channel);
            //将ViewHolder存储在View中
            convertView.setTag(holder);
        }else{
            //重新获取ViewHolder
            holder= (ViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        ResultBeanData.ResultBean.ChannelInfoBean channelInfoBean = mChannelInfoBeans.get(position);
        //设置数据
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE + channelInfoBean.getImage()).into(holder.iv_icon);
        holder.tv_title.setText(channelInfoBean.getChannelName());
        return convertView;
    }

    static class ViewHolder{
        ImageView iv_icon;
        TextView tv_title;
    }
}
