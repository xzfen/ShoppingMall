package com.feng.shoppingmall.home.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.feng.shoppingmall.R;
import com.feng.shoppingmall.base.BaseFragment;
import com.feng.shoppingmall.home.adapter.HomeFragmentAdapter;
import com.feng.shoppingmall.home.bean.ResultBeanData;
import com.feng.shoppingmall.home.callback.IHomeCallback;
import com.feng.shoppingmall.home.presenter.HomePresenterImpl;
import com.feng.shoppingmall.utils.Constants;
import com.feng.shoppingmall.utils.OkHttpUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.home.fragment
 * @作者：FENG
 * @类名：HomeFragment
 * @创建时间：2023/1/820:49
 * @描述：主页的Fragment
 **/
public class HomeFragment extends BaseFragment implements IHomeCallback {
    private static final String TAG = HomeFragment.class.getSimpleName();
    //private ResultBean resultBean;
    private RecyclerView rvHome;
    private ImageView ib_top;
    private HomeFragmentAdapter adapter;
    private TextView tv_search_home;
    private TextView tv_message_home;
    private HomePresenterImpl mHomePresenter;


    @Override
    public View initView() {
        Log.d(TAG,"主页面的Fragment的UI被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_home,null);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);
        return view;
    }

    @Override
    protected void initListener() {
        //置顶的监听
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "置顶", Toast.LENGTH_SHORT).show();
                rvHome.scrollToPosition(0);
            }
        });

        //搜素的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
            }
        });

        //消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(mContext, MessageCenterActivity.class);
                //mContext.startActivity(intent);
            }
        });

    }

    @Override
    public void initPresenter() {
        //创建Presenter
        mHomePresenter = new HomePresenterImpl();
        mHomePresenter.registerViewCallback(this);
    }

    @Override
    protected void initData() {
        mHomePresenter.getHomeContentData();
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onHomeContentsLoaded(ResultBeanData resultBeanData) {
        if(resultBeanData!=null){
            //设置适配器
            adapter = new HomeFragmentAdapter(mContext, resultBeanData.getResult());
            rvHome.setAdapter(adapter);
            GridLayoutManager manager = new GridLayoutManager(mContext, 1);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(position<=3){
                        //隐藏
                        ib_top.setVisibility(View.GONE);
                    }else{
                        ib_top.setVisibility(View.VISIBLE);
                    }
                    return 1;
                }
            });
            //设置布局管理器
            rvHome.setLayoutManager(manager);
        }else{
            //没有数据
        }
    }
}
