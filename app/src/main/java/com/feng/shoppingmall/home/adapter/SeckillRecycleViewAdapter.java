package com.feng.shoppingmall.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.feng.shoppingmall.R;
import com.feng.shoppingmall.home.bean.ResultBeanData;
import com.feng.shoppingmall.utils.Constants;

import java.util.List;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.home.adapter
 * @作者：FENG
 * @类名：ReckillRecycleViewAdapter
 * @创建时间：2023/1/1314:06
 * @描述：
 **/
//********新建SeckillRecycleViewAdapter继承自RecyclerView.Adapter，并将泛型指定为子类的ViewHolder********
public class SeckillRecycleViewAdapter extends RecyclerView.Adapter<SeckillRecycleViewAdapter.ViewHolder> {
    private static final String TAG = HomeFragmentAdapter.class.getSimpleName();
    private final List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list;
    private Context mContext;

    /**
     * 2.根据传入的Item视图，获取View实例
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv_figure = itemView.findViewById(R.id.iv_figure);
            tv_cover_price = itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = itemView.findViewById(R.id.tv_origin_price);
            //设置按键监听方式一：在ViewHolder里设置
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(mContext,"秒杀="+getLayoutPosition(),Toast.LENGTH_SHORT).show();
                    if(onSeckillView!=null){
                        onSeckillView.onItemClick(getLayoutPosition());
                    }
                }
            });
            /*iv_figure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "秒杀图片=" + getLayoutPosition(), Toast.LENGTH_SHORT).show();
                }
            });*/
        }
    }

    public SeckillRecycleViewAdapter(Context context, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> data) {
        this.mContext = context;
        this.list = data;
    }

    /**
     * 1.创建Item视图，并返回相应的ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //将layout文件反射到View中
        View view = View.inflate(mContext, R.layout.item_seckill, null);
        ViewHolder holder=new ViewHolder(view);

        //根据传入的View初始化一个ViewHolder
        return holder;
    }

    /**
     * 绑定数据到正确的Item视图上
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //1.根据position得到对应的数据
        ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = list.get(position);
        //2.绑定数据
        Log.d(TAG, "onBindViewHolder: " + holder.toString());
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE + listBean.getFigure()).into(holder.iv_figure);
        holder.tv_cover_price.setText(listBean.getCoverPrice());
        holder.tv_origin_price.setText(listBean.getOriginPrice());

        //设置按键监听方式二：在onBindViewHolder里设置
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "秒杀=" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.iv_figure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "秒杀图片=" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    /**
     * 返回该Adapter所持有的Itme数量
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    //设置按键监听方式三：在回调函数Callback里设置
    interface OnSeckillView {
        public void onItemClick(int position);
    }
    private OnSeckillView onSeckillView;

    public void setOnSeckillView(OnSeckillView onSeckillView) {
        this.onSeckillView = onSeckillView;
    }
}
