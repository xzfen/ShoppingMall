package com.feng.shoppingmall.shoppingcart.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.feng.shoppingmall.R;
import com.feng.shoppingmall.base.BaseFragment;
import com.feng.shoppingmall.home.bean.GoodsBean;
import com.feng.shoppingmall.shoppingcart.adapter.ShopCartAdapter;
import com.feng.shoppingmall.shoppingcart.utils.CartStorage;

import java.util.List;

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
    private LinearLayout ll_empty_shopcart;

    private ShopCartAdapter adapter;
    //编辑状态
    private static final int ACTION_EDIT = 0;
    //完成状态
    private static final int ACTION_COMPLETE = 1;

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
         *       3:FrameLayout
         *           1:LinearLayout
         *               1:RecyclerView
         *               2:LinearLayout(结算)
         *               3:LinearLayout(删除)
         *           2:购车显示布局 empty_cart.xml
         * */
        View view = View.inflate(mContext, R.layout.fragment_shopping_cart, null);
        tvShopcartEdit = (TextView) view.findViewById(R.id.tv_shopcart_edit);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) view.findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) view.findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) view.findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) view.findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) view.findViewById(R.id.ll_delete);
        cbAll = (CheckBox) view.findViewById(R.id.cb_all);
        btnDelete = (Button) view.findViewById(R.id.btn_delete);
        btnCollection = (Button) view.findViewById(R.id.btn_collection);

        ivEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        tvEmptyCartTobuy = (TextView) view.findViewById(R.id.tv_empty_cart_tobuy);
        ll_empty_shopcart = view.findViewById(R.id.ll_empty_shopcart);
        btnCheckOut.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCollection.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        hideDelete();
        showData();
    }

    @Override
    protected void initListener() {
        //设置默认的编辑状态
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setText("编辑");
        //显示去结算布局
        llCheckAll.setVisibility(View.VISIBLE);
        tvShopcartEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.得到状态
                int action = (int) v.getTag();
                //2.根据不同状态做不同的处理
                if (action == ACTION_EDIT) {
                    //切换完成状态
                    showDelete();
                } else {
                    //切换成编辑状态
                    hideDelete();
                }
            }
        });
    }

    private void hideDelete() {
        //1.设置状态和文本“编辑”
        tvShopcartEdit.setText("编辑");
        tvShopcartEdit.setTag(ACTION_EDIT);
        //2.变成勾选
        if (adapter != null) {
            //adapter.checkAll_none(true);
            adapter.checkAll();
            adapter.showTotalPrice();
        }
        //3.删除视图隐藏
        llDelete.setVisibility(View.GONE);
        //4.结算视图显示
        llCheckAll.setVisibility(View.VISIBLE);

    }

    private void showDelete() {
        //1.设置状态和文本“完成”
        tvShopcartEdit.setText("完成");
        tvShopcartEdit.setTag(ACTION_COMPLETE);
        //2.变成非勾选
        if (adapter != null) {
            //adapter.checkAll_none(false);
            adapter.checkAll();
        }
        //3.删除视图显示
        llDelete.setVisibility(View.VISIBLE);
        //4.结算视图隐藏
        llCheckAll.setVisibility(View.GONE);

        //adapter.showTotalPrice();
    }

    @Override
    public void onClick(View v) {
        if (v == btnCheckOut) {
            // Handle clicks for btnCheckOut
        } else if (v == btnDelete) {
            // 删除选中的item，然后校验全选状态
            adapter.deleteData();
            //hideDelete();
            //adapter.showTotalPrice();
            //adapter.checkAll();
            //showData();
            if (adapter.getItemCount()==0){
                ll_empty_shopcart.setVisibility(View.VISIBLE);
                tvShopcartEdit.setVisibility(View.GONE);
                llDelete.setVisibility(View.GONE);
            }
        } else if (v == btnCollection) {
            // Handle clicks for btnCollection
        }
    }

    @Override
    public void initData() {
        super.initData();
        Log.d(TAG, "购物车页面的Fragment的数据被初始化了");
        showData();
    }

    /**
     * 显示数据
     */
    private void showData() {
        List<GoodsBean> datas = CartStorage.getInstance().getAllData();
        if (datas != null && datas.size() > 0) {
            tvShopcartEdit.setVisibility(View.VISIBLE);
            //设置适配器
            adapter = new ShopCartAdapter(mContext, datas, tvShopcartTotal, checkboxAll, cbAll);
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerview.setAdapter(adapter);
            //隐藏空布局
            ll_empty_shopcart.setVisibility(View.GONE);
        } else {
            //没有数据，显示空的布局
            tvShopcartEdit.setVisibility(View.GONE);
            ll_empty_shopcart.setVisibility(View.VISIBLE);


        }
    }
}
