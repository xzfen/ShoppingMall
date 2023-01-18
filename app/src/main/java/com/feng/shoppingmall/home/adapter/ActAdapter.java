package com.feng.shoppingmall.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.feng.shoppingmall.app.GoodsInfoActivity;
import com.feng.shoppingmall.home.bean.ResultBeanData;
import com.feng.shoppingmall.utils.Constants;

import java.util.List;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.home.adapter
 * @作者：FENG
 * @类名：ActAdapter
 * @创建时间：2023/1/138:16
 * @描述：
 **/
public class ActAdapter extends PagerAdapter {
    private Context mContext;
    private List<ResultBeanData.ResultBean.ActInfoBean> mActInfoBeans;
    public ActAdapter(Context context, List<ResultBeanData.ResultBean.ActInfoBean> data) {
        this.mContext=context;
        mActInfoBeans=data;
    }

    @Override
    public int getCount() {
        return mActInfoBeans.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ResultBeanData.ResultBean.ActInfoBean actInfoBean = mActInfoBeans.get(position);
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE+actInfoBean.getIconUrl()).into(imageView);
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"position: " + position,Toast.LENGTH_SHORT).show();
                //启动商品详情页面
                Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                mContext.startActivity(intent);
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
