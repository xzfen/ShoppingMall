package com.feng.shoppingmall.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.feng.shoppingmall.app.GoodsInfoActivity;
import com.feng.shoppingmall.home.bean.ResultBeanData;
import com.feng.shoppingmall.utils.Constants;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.holder.BannerImageHolder;

import java.util.List;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.home.adapter
 * @作者：FENG
 * @类名：BannerImageAdapter
 * @创建时间：2023/1/1016:58
 * @描述：
 **/
public class BannerImageAdapter extends BannerAdapter<ResultBeanData.ResultBean.BannerInfoBean, BannerImageAdapter.BannerViewHolder> {

    private Context mContext;
    private List<ResultBeanData.ResultBean.BannerInfoBean> mBannerInfoBeans;
    public BannerImageAdapter(Context context,List<ResultBeanData.ResultBean.BannerInfoBean> data) {
        super(data);
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        this.mContext=context;
        mBannerInfoBeans = data;
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, ResultBeanData.ResultBean.BannerInfoBean data, int position, int size) {
        String url = data.getImage();
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE+url).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"position: " + position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public BannerViewHolder(@NonNull ImageView itemView) {
            super(itemView);
            imageView = itemView;
        }
    }
}
