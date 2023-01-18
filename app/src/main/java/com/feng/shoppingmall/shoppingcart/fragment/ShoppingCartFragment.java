package com.feng.shoppingmall.shoppingcart.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.feng.shoppingmall.R;
import com.feng.shoppingmall.base.BaseFragment;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.home.fragment
 * @作者：FENG
 * @类名：HomeFragment
 * @创建时间：2023/1/820:49
 * @描述：购物车的Fragment
 **/
public class ShoppingCartFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = ShoppingCartFragment.class.getSimpleName();
    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2023-01-18 14:50:33 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */

    @Override
    public View initView() {
        /*
        * 页面分析
        *    最外边是 LinearLayout
        *       1:相对布局
        *       2:View 分割线
        *       3:FrameLayou
        *           1:LinearLayout
        *               1:RecyclerView
        *               2:LinearLayout(结算)
        *               3:LinearLayout(删除)
        *           2:购车显示布局 empty_cart.xml
        * */
        View view = View.inflate(mContext, R.layout.fragment_shopping_cart,null);
        tvShopcartEdit = (TextView)view.findViewById( R.id.tv_shopcart_edit );
        recyclerview = (RecyclerView)view.findViewById( R.id.recyclerview );
        llCheckAll = (LinearLayout)view.findViewById( R.id.ll_check_all );
        checkboxAll = (CheckBox)view.findViewById( R.id.checkbox_all );
        tvShopcartTotal = (TextView)view.findViewById( R.id.tv_shopcart_total );
        btnCheckOut = (Button)view.findViewById( R.id.btn_check_out );
        llDelete = (LinearLayout)view.findViewById( R.id.ll_delete );
        cbAll = (CheckBox)view.findViewById( R.id.cb_all );
        btnDelete = (Button)view.findViewById( R.id.btn_delete );
        btnCollection = (Button)view.findViewById( R.id.btn_collection );

        ivEmpty = (ImageView)view.findViewById( R.id.iv_empty );
        tvEmptyCartTobuy = (TextView)view.findViewById( R.id.tv_empty_cart_tobuy );

        btnCheckOut.setOnClickListener( this );
        btnDelete.setOnClickListener( this );
        btnCollection.setOnClickListener( this );
        return view;
    }

    @Override
    public void onClick(View v) {
        if ( v == btnCheckOut ) {
            // Handle clicks for btnCheckOut
        } else if ( v == btnDelete ) {
            // Handle clicks for btnDelete
        } else if ( v == btnCollection ) {
            // Handle clicks for btnCollection
        }
    }

    @Override
    public void initData() {
        Log.d(TAG,"购物车页面的Fragment的数据被初始化了");
    }
}
