package com.feng.shoppingmall.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.feng.shoppingmall.R;
import com.feng.shoppingmall.app.GoodsInfoActivity;
import com.feng.shoppingmall.home.bean.GoodsBean;
import com.feng.shoppingmall.home.bean.ResultBeanData;
import com.youth.banner.Banner;
import com.youth.banner.indicator.RectangleIndicator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.home.adapter
 * @作者：FENG
 * @类名：HomeRecycleAdapter
 * @创建时间：2023/1/919:37
 * @描述：
 **/
public class HomeFragmentAdapter extends RecyclerView.Adapter {
    //6种类型
    //横幅广告
    public static final int BANNER = 0;
    //频道
    public static final int CHANNEL = 1;
    //活动
    public static final int ACT = 2;
    //秒杀
    public static final int SECKILL = 3;
    //推荐
    public static final int RECOMMEND = 4;
    //热卖
    public static final int HOT = 5;
    private static final String TAG = HomeFragmentAdapter.class.getSimpleName();
    public static final String GOODS_BEAN = "goodsBean";

    private Context mContext;
    //获取的数据
    private ResultBeanData.ResultBean mResultBean;
    //当前类型
    public int currentType = BANNER;
    //用来初始化布局
    private LayoutInflater mLayoutInflater;

    public HomeFragmentAdapter(Context context, ResultBeanData.ResultBean resultBean) {
        this.mContext=context;
        this.mResultBean=resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * 根据位置得到类型-系统调用
     * @param position
     * @return
     * */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    /**
     * 返回总条数，共六种类型
     * 开发过程中从1-->2...
     * @return
     */
    @Override
    public int getItemCount() {
        return 6;
    }

    /**
     * 相当于getView中创建ViewHolder模块
     * 创建ViewHolder
     * @param parent
     * @param viewType 当前的类型
     * @return
     * */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext,mLayoutInflater.inflate(R.layout.banner_viewpager,null));
        } else if(viewType == CHANNEL) {
            return new ChannelViewHolder(mContext,mLayoutInflater.inflate(R.layout.channel_item,null));
        }else if(viewType == ACT) {
            return new ActViewHolder(mContext,mLayoutInflater.inflate(R.layout.act_item,null));
        }else if(viewType == SECKILL) {
            return new SeckillViewHolder(mContext,mLayoutInflater.inflate(R.layout.seckill_item,null));
        }else if(viewType == RECOMMEND) {
            return new RecommendViewHolder(mContext,mLayoutInflater.inflate(R.layout.recommend_item,null));
        }else if(viewType == HOT) {
            return new HotViewHolder(mContext,mLayoutInflater.inflate(R.layout.hot_item,null));
        }
        return null;
    }

    /**
     * 相当于getView中的绑定数据模块
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(mResultBean.getBannerInfo());
        }else if(getItemViewType(position)==CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(mResultBean.getChannelInfo());
        }else if(getItemViewType(position)==ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(mResultBean.getActInfo());
        }else if(getItemViewType(position)==SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(mResultBean.getSeckillInfo());
        }else if(getItemViewType(position)==RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(mResultBean.getRecommendInfo());
        }else if(getItemViewType(position)==HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(mResultBean.getHotInfo());
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private Banner banner;
        public BannerViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            this.mContext = context;
            this.banner = itemView.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> bannerInfo) {
            //创建banner适配器，并传入数据
            BannerImageAdapter adapter = new BannerImageAdapter(mContext,bannerInfo);
            banner.setAdapter(adapter)
                    .setIndicator(new RectangleIndicator(mContext));
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        GridView gv_channel;

        public ChannelViewHolder(Context context, View itemView) {
            super(itemView);
            this.mContext=context;
            this.gv_channel = itemView.findViewById(R.id.gv_channel);
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext,"position: " + position,Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channelInfo) {
            //创建channel适配器，并传入数据
            ChannelAdapter adapter = new ChannelAdapter(mContext,channelInfo);
            gv_channel.setAdapter(adapter);
        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ViewPager act_viewpager;
        public ActViewHolder(Context context, View itemView) {
            super(itemView);
            mContext=context;
            act_viewpager=itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(List<ResultBeanData.ResultBean.ActInfoBean> actInfo) {
            //设置每个页面的间距
            act_viewpager.setPageMargin(20);
            //设置左右各预加载3个页面
            act_viewpager.setOffscreenPageLimit(3);
            //TODO:设置滑动动画，未实现
            //act_viewpager.setPageTransformer(true,new ZoomOutPageTransformer());
            //创建act适配器，并传入数据
            ActAdapter adapter = new ActAdapter(mContext, actInfo);
            act_viewpager.setAdapter(adapter);
        }
    }

    class SeckillViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;
        private int dt;

        private Handler handler=new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what==0){
                    dt = dt - 1000;
                    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                    String time = formatter.format(new Date(dt));
                    //Java1.8之后使用这个DateTimeFormatter类格式化时间
                    /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String time = formatter.format(downTimer);*/
                    tv_time_seckill.setText(time);
                    handler.removeMessages(0);
                    handler.sendEmptyMessageDelayed(0, 1000);
                    if (dt < 0) {
                        handler.removeCallbacksAndMessages(null);
                    }
                }

            }
        };

        public SeckillViewHolder(Context context, View itemView) {
            super(itemView);
            this.mContext=context;
            tv_time_seckill=itemView.findViewById(R.id.tv_time_seckill);
            tv_more_seckill=itemView.findViewById(R.id.tv_more_seckill);
            rv_seckill=itemView.findViewById(R.id.rv_seckill);
        }

        public void setData(ResultBeanData.ResultBean.SeckillInfoBean seckillInfo) {
            //设置布局管理器
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            //创建rv_seckill的Adapter适配器，并传入数据
            SeckillRecycleViewAdapter adapter = new SeckillRecycleViewAdapter(mContext,seckillInfo.getList());
            rv_seckill.setAdapter(adapter);
            //设置点击事件
            adapter.setOnSeckillView(new SeckillRecycleViewAdapter.OnSeckillView() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(mContext, "秒杀=" + position, Toast.LENGTH_SHORT).show();
                    //获取秒杀商品列表
                    List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list = seckillInfo.getList();
                    //获取商品详细信息Bean类
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setFigure(list.get(position).getFigure());
                    goodsBean.setName(list.get(position).getName());
                    goodsBean.setCover_price(list.get(position).getCoverPrice());
                    goodsBean.setProduct_id(list.get(position).getProductId());
                    //启动商品详情页面
                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN,goodsBean);
                    mContext.startActivity(intent);
                }
            });

            //设置倒计时时间
            dt=Integer.parseInt(seckillInfo.getEndTime())-Integer.parseInt(seckillInfo.getStartTime());
            //倒计时
            handler.sendEmptyMessage(0);
        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private TextView tv_more_recommend;
        private GridView gv_recommend;

        public RecommendViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            this.mContext=context;
            tv_more_recommend = itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = itemView.findViewById(R.id.gv_recommend);
        }

        public void setData(List<ResultBeanData.ResultBean.RecommendInfoBean> recommendInfo) {
            //创建Recommend适配器，并传入数据
            RecommendAdapter recommendAdapter = new RecommendAdapter(mContext,recommendInfo);
            gv_recommend.setAdapter(recommendAdapter);
            //设置点击事件
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext,"position: " + position,Toast.LENGTH_SHORT).show();
                    ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = recommendInfo.get(position);
                    //获取商品详细信息Bean类
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setProduct_id(recommendInfoBean.getProductId());
                    goodsBean.setName(recommendInfoBean.getName());
                    goodsBean.setCover_price(recommendInfoBean.getCoverPrice());
                    goodsBean.setFigure(recommendInfoBean.getFigure());
                    //启动商品详情页面
                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN,goodsBean);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder{
        private final Context mContext;
        private TextView tv_more_hot;
        private GridView gv_hot;

        public HotViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            this.mContext=context;
            tv_more_hot=itemView.findViewById(R.id.tv_more_hot);
            gv_hot=itemView.findViewById(R.id.gv_hot);
        }

        public void setData(List<ResultBeanData.ResultBean.HotInfoBean> hotInfo) {
            //创建Hot适配器，并传入数据
            HotAdapter adapter=new HotAdapter(mContext,hotInfo);
            gv_hot.setAdapter(adapter);

            //设置点击事件
            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext,"position: " + position,Toast.LENGTH_SHORT).show();
                    ResultBeanData.ResultBean.HotInfoBean hotInfoBean = hotInfo.get(position);
                    //获取商品详细信息Bean类
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setFigure(hotInfoBean.getFigure());
                    goodsBean.setName(hotInfoBean.getName());
                    goodsBean.setCover_price(hotInfoBean.getCoverPrice());
                    goodsBean.setProduct_id(hotInfoBean.getProductId());
                    //启动商品详情页面
                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN,goodsBean);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
