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
            Toast.makeText(this,"??????????????????",Toast.LENGTH_SHORT).show();
        } else if ( v == tvGoodInfoCallcenter ) {
            // Handle clicks for tvGoodInfoCallcenter
            Toast.makeText(this,"????????????",Toast.LENGTH_SHORT).show();
        }else if ( v == tvGoodInfoCollection ) {
            // Handle clicks for tvGoodInfoCollection
            Toast.makeText(this,"??????",Toast.LENGTH_SHORT).show();
        }else if ( v == tvGoodInfoCart ) {
            // Handle clicks for tvGoodInfoCart
            Toast.makeText(this,"?????????",Toast.LENGTH_SHORT).show();
        }else if ( v == tv_more_share ) {
            // Handle clicks for tv_more_share
            Toast.makeText(this,"??????",Toast.LENGTH_SHORT).show();
        }else if ( v == tv_more_search ) {
            // Handle clicks for tv_more_search
            Toast.makeText(this,"??????",Toast.LENGTH_SHORT).show();
        }else if ( v == tv_more_home ) {
            // Handle clicks for tv_more_home
            Toast.makeText(this,"?????????",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /*
        * ????????????????????????
        * 1????????????
        * 2????????????
        * 3????????????
        *   1:????????????
        *       ????????? ScrollViewContainer ???????????? ScrollView
        *   2:????????????
        *       ????????????????????????????????????
        *   3:??????
        *       ????????? ??????????????????
        * */
        setContentView(R.layout.activity_goods_info);
        findViews();
        //????????????
        goodsBean = (GoodsBean) getIntent().getSerializableExtra(HomeFragmentAdapter.GOODS_BEAN);
        if (goodsBean!=null) {
            setDataForView(goodsBean);
        }
    }

    private void setDataForView(GoodsBean goodsBean) {
        //???????????????????????????
        Glide.with(this).load(Constants.BASE_URl_IMAGE+goodsBean.getFigure()).into(ivGoodInfoImage);
        tvGoodInfoName.setText(goodsBean.getName());
        tvGoodInfoPrice.setText("???"+goodsBean.getCover_price());
        //
        setWebViewData(goodsBean.getProduct_id());
    }

    private void setWebViewData(String product_id) {
        if (product_id!=null) {
            //?????????????????????????????????????????????,???????????????
            //http://10.0.2.2:8080/atguigu/json/GOODSINFO_URL.json2691
            //wbGoodInfoMore.loadUrl(Constants.GOODSINFO_URL + product_id);
            wbGoodInfoMore.loadUrl("http://www.atguigu.com");
            //??????WebView?????????????????????????????????????????????????????????????????????????????????WebView??????
            wbGoodInfoMore.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //????????????true??????????????????WebView????????????false??????????????????????????????????????????
                    view.loadUrl(url);
                    return true;
                }
            });
            WebSettings settings = wbGoodInfoMore.getSettings();
            //????????????javascript
            settings.setJavaScriptEnabled(true);
            //??????Webivew??????<meta>?????????viewport????????????????????????????????????????????????
            settings.setUseWideViewPort(true);
            //??????????????????
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
    }
}