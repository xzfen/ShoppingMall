package com.feng.shoppingmall.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.feng.shoppingmall.R;
import com.feng.shoppingmall.home.adapter.HomeFragmentAdapter;
import com.feng.shoppingmall.home.bean.GoodsBean;
import com.feng.shoppingmall.shoppingcart.utils.CartStorage;
import com.feng.shoppingmall.utils.Constants;

import java.io.Serializable;

public class GoodsInfoActivity extends Activity implements View.OnClickListener{
    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private WebView wbGoodInfoMore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;
    private LinearLayout ll_root;
    private TextView tv_more_share;
    private TextView tv_more_search;
    private TextView tv_more_home;
    private TextView btn_more;
    private GoodsBean goodsBean;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2023-01-16 14:13:13 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        ll_root=findViewById(R.id.ll_root);
        tv_more_share=findViewById(R.id.tv_more_share);
        tv_more_search=findViewById(R.id.tv_more_search);
        tv_more_home=findViewById(R.id.tv_more_home);
        btn_more=findViewById(R.id.btn_more);

        ibGoodInfoBack = (ImageButton)findViewById( R.id.ib_good_info_back );
        ibGoodInfoMore = (ImageButton)findViewById( R.id.ib_good_info_more );
        ivGoodInfoImage = (ImageView)findViewById( R.id.iv_good_info_image );
        tvGoodInfoName = (TextView)findViewById( R.id.tv_good_info_name );
        tvGoodInfoDesc = (TextView)findViewById( R.id.tv_good_info_desc );
        tvGoodInfoPrice = (TextView)findViewById( R.id.tv_good_info_price );
        tvGoodInfoStore = (TextView)findViewById( R.id.tv_good_info_store );
        tvGoodInfoStyle = (TextView)findViewById( R.id.tv_good_info_style );
        wbGoodInfoMore = (WebView)findViewById( R.id.wb_good_info_more );
        llGoodsRoot = (LinearLayout)findViewById( R.id.ll_goods_root );
        tvGoodInfoCallcenter = (TextView)findViewById( R.id.tv_good_info_callcenter );
        tvGoodInfoCollection = (TextView)findViewById( R.id.tv_good_info_collection );
        tvGoodInfoCart = (TextView)findViewById( R.id.tv_good_info_cart );
        btnGoodInfoAddcart = (Button)findViewById( R.id.btn_good_info_addcart );

        ibGoodInfoBack.setOnClickListener( this );
        ibGoodInfoMore.setOnClickListener( this );
        btnGoodInfoAddcart.setOnClickListener( this );

        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);

        tv_more_share.setOnClickListener(this);
        tv_more_search.setOnClickListener(this);
        tv_more_home.setOnClickListener(this);
        btn_more.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2023-01-16 14:13:13 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == ibGoodInfoBack ) {
            // Handle clicks for ibGoodInfoBack
            finish();
        } else if ( v == ibGoodInfoMore ) {
            // Handle clicks for ibGoodInfoMore
            if(ll_root.getVisibility()==View.VISIBLE){
                ll_root.setVisibility(View.GONE);
            }else{
                ll_root.setVisibility(View.VISIBLE);
            }
        } else if ( v == btnGoodInfoAddcart ) {
            // Handle clicks for btnGoodInfoAddcart
            CartStorage.getInstance().addData(goodsBean);
            Toast.makeText(this,"添加到购物车",Toast.LENGTH_SHORT).show();
        } else if ( v == tvGoodInfoCallcenter ) {
            // Handle clicks for tvGoodInfoCallcenter
            Toast.makeText(this,"客户中心",Toast.LENGTH_SHORT).show();
        }else if ( v == tvGoodInfoCollection ) {
            // Handle clicks for tvGoodInfoCollection
            Toast.makeText(this,"收藏",Toast.LENGTH_SHORT).show();
        }else if ( v == tvGoodInfoCart ) {
            // Handle clicks for tvGoodInfoCart
            Toast.makeText(this,"购物车",Toast.LENGTH_SHORT).show();
        }else if ( v == tv_more_share ) {
            // Handle clicks for tv_more_share
            Toast.makeText(this,"分享",Toast.LENGTH_SHORT).show();
        }else if ( v == tv_more_search ) {
            // Handle clicks for tv_more_search
            Toast.makeText(this,"搜索",Toast.LENGTH_SHORT).show();
        }else if ( v == tv_more_home ) {
            // Handle clicks for tv_more_home
            Toast.makeText(this,"主页面",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /*
        * 布局分成三部分：
        * 1：标题栏
        * 2：分割线
        * 3：帧布局
        *   1:线性布局
        *       里面用 ScrollViewContainer 嵌套两个 ScrollView
        *   2:线性布局
        *       客服联系，收藏，购物车等
        *   3:更多
        *       分享， 搜索，首页等
        * */
        setContentView(R.layout.activity_goods_info);
        findViews();
        //接收数据
        goodsBean = (GoodsBean) getIntent().getSerializableExtra(HomeFragmentAdapter.GOODS_BEAN);
        if (goodsBean!=null) {
            setDataForView(goodsBean);
        }
    }

    private void setDataForView(GoodsBean goodsBean) {
        //设置图片名称和价格
        Glide.with(this).load(Constants.BASE_URl_IMAGE+goodsBean.getFigure()).into(ivGoodInfoImage);
        tvGoodInfoName.setText(goodsBean.getName());
        tvGoodInfoPrice.setText("￥"+goodsBean.getCover_price());
        //
        setWebViewData(goodsBean.getProduct_id());
    }

    private void setWebViewData(String product_id) {
        if (product_id!=null) {
            //使用模拟器，使用下面的资源链接,目前不可用
            //http://10.0.2.2:8080/atguigu/json/GOODSINFO_URL.json2691
            //wbGoodInfoMore.loadUrl(Constants.GOODSINFO_URL + product_id);
            wbGoodInfoMore.loadUrl("http://www.atguigu.com");
            //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
            wbGoodInfoMore.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //返回值是true的时候控制为WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
            });
            WebSettings settings = wbGoodInfoMore.getSettings();
            //启用支持javascript
            settings.setJavaScriptEnabled(true);
            //设置Webivew支持<meta>标签的viewport属性，这样网页可以自适应控件大小
            settings.setUseWideViewPort(true);
            //优先使用缓存
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
    }
}