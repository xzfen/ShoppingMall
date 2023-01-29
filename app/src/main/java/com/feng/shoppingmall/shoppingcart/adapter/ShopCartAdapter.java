package com.feng.shoppingmall.shoppingcart.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.feng.shoppingmall.R;
import com.feng.shoppingmall.home.bean.GoodsBean;
import com.feng.shoppingmall.shoppingcart.utils.CartStorage;
import com.feng.shoppingmall.shoppingcart.view.NumberAddSubView;
import com.feng.shoppingmall.utils.Constants;

import java.util.Iterator;
import java.util.List;

/**
 * @项目名称：ShoppingMall
 * @包名：com.feng.shoppingmall.shoppingcart.adapter
 * @作者：FENG
 * @类名：ShopCartAdapter
 * @创建时间：2023/1/2412:30
 * @描述：
 **/
public class ShopCartAdapter extends RecyclerView.Adapter<ShopCartAdapter.ViewHolder> {
    private final Context mContext;
    private final List<GoodsBean> datas;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    //完成状态下的删除Checkbox
    private final CheckBox cbAll;

    public ShopCartAdapter(Context context, List<GoodsBean> datas, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox cbAll) {
        this.mContext=context;
        this.datas=datas;
        this.tvShopcartTotal=tvShopcartTotal;
        this.checkboxAll=checkboxAll;
        this.cbAll=cbAll;

        showTotalPrice();
        //设置点击事件
        setListener();
        //校验是否全选
        checkAll();
    }

    private void setListener() {
        //设置Checkbox点击事件
        setOnItemCheckboxClickListener(new OnItemCheckboxClickListener() {
            @Override
            public void onItemCheckboxClick(int position) {
                //1.根据位置找到对应的Bean对象
                GoodsBean goodsBean = datas.get(position);
                //2.设置取反状态
                goodsBean.setChildSelected(!goodsBean.isChildSelected());
                //3.刷新状态
                //notifyItemChanged(position);
                //4.校验是否全新
                checkAll();
                //5.重新计算总价格
                showTotalPrice();
            }
        });
        //设置checkboxAll的点击事件
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.得到状态
                boolean checked = checkboxAll.isChecked();
                //2.根据状态设置全选和非全选
                checkAll_none(checked);
                //3.计算总价格
                showTotalPrice();
            }
        });
        //设置cbAll的点击事件
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.得到状态
                boolean checked = cbAll.isChecked();
                //2.根据状态设置全选和非全选
                checkAll_none(checked);
                //3.计算总价格
                //showTotalPrice();
            }
        });
    }
    //设置全选和非全选
    public void checkAll_none(boolean checked) {
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                datas.get(i).setChildSelected(checked);
                checkboxAll.setChecked(checked);
                notifyItemChanged(i);
            }
        } else {
            checkboxAll.setChecked(false);

        }
    }

    public void checkAll() {
        if (datas != null && datas.size() > 0) {
            int number=0;
            for (int i = 0; i < datas.size(); i++) {
                if (!datas.get(i).isChildSelected()) {
                    //非全选
                    checkboxAll.setChecked(false);
                    cbAll.setChecked(false);
                } else {
                    number++;
                }
                if (number==datas.size()) {
                    //全选
                    checkboxAll.setChecked(true);
                    cbAll.setChecked(true);
                }
            }
        }else {
            checkboxAll.setChecked(false);
            cbAll.setChecked(false);
        }
    }

    public void showTotalPrice() {
        tvShopcartTotal.setText("合计："+getTotalPrice());
    }

    private double getTotalPrice() {
        double totalPrice=0.0;
        if (datas!=null&&datas.size()>0){
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if(goodsBean.isChildSelected()){
                    totalPrice=totalPrice+goodsBean.getNumber()*Double.parseDouble(goodsBean.getCover_price());
                }
            }
        }
        return totalPrice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView=View.inflate(mContext, R.layout.item_shop_cart,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //1.根据位置得到对应的Bean对象
        GoodsBean goodsBean = datas.get(position);
        //2.设置数据
        holder.cb_gov.setChecked(goodsBean.isChildSelected());
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE +goodsBean.getFigure()).into(holder.iv_gov);
        holder.tv_desc_gov.setText(goodsBean.getName());
        holder.tv_price_gov.setText("￥" + goodsBean.getCover_price());
        holder.numberAddSubView.setValue(goodsBean.getNumber());
        holder.numberAddSubView.setMinValue(1);
        holder.numberAddSubView.setMaxValue(8);
        //设置商品数量的变化
        holder.numberAddSubView.setOnNumberChangeListener(new NumberAddSubView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(int value) {
                //1.当前列表内存红
                goodsBean.setNumber(value);
                //2.本地要更新
                CartStorage.getInstance().updataData(goodsBean);
                //3.刷新适配器
                //notifyItemChanged(position);
                //4.再次计算总价格
                showTotalPrice();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void deleteData() {
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                //删除选中的
                GoodsBean goodsBean = datas.get(i);
                if(goodsBean.isChildSelected()){
                    //内存移除
                    datas.remove(goodsBean);
                    //保持到本地
                    CartStorage.getInstance().deleteData(goodsBean);
                    //刷新
                    notifyItemRemoved(i);
                    i--;
                }
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cb_gov;
        private ImageView iv_gov;
        private TextView tv_desc_gov;
        private TextView tv_price_gov;
        private NumberAddSubView numberAddSubView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cb_gov = (CheckBox) itemView.findViewById(R.id.cb_gov);
            iv_gov = (ImageView) itemView.findViewById(R.id.iv_gov);
            tv_desc_gov = (TextView) itemView.findViewById(R.id.tv_desc_gov);
            tv_price_gov = (TextView) itemView.findViewById(R.id.tv_price_gov);
            numberAddSubView = (NumberAddSubView) itemView.findViewById(R.id.numberAddSubView);
            //设置item的点击事件
            cb_gov.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemCheckboxClickListener!=null){
                        onItemCheckboxClickListener.onItemCheckboxClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    public interface OnItemCheckboxClickListener{
        public void onItemCheckboxClick(int position);
    }

    public void setOnItemCheckboxClickListener(OnItemCheckboxClickListener onItemCheckboxClickListener) {
        this.onItemCheckboxClickListener = onItemCheckboxClickListener;
    }

    /**
     * 点击item某条checkbox的时候回调
     * */
    private OnItemCheckboxClickListener onItemCheckboxClickListener;
}
