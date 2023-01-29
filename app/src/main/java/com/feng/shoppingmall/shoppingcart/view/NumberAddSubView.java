package com.feng.shoppingmall.shoppingcart.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.feng.shoppingmall.R;

public class NumberAddSubView extends LinearLayout implements View.OnClickListener {
    private ImageView btn_sub;
    private ImageView btn_add;
    private TextView tv_count;
    private int value = 1;
    private int minValue = 1;
    private int maxValue = 10;

    public int getValue() {
        String countStr = tv_count.getText().toString().trim();//文本内容
        if (!TextUtils.isEmpty(countStr)) {
            value = Integer.parseInt(countStr);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tv_count.setText(String.valueOf(value));
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public NumberAddSubView(Context context) {
        this(context, null);
    }

    public NumberAddSubView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberAddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //把布局和当前类形成整体
        View.inflate(context, R.layout.number_add_sub_layout, this);
        btn_sub = (ImageView) findViewById(R.id.btn_sub);
        btn_add = (ImageView) findViewById(R.id.btn_add);
        tv_count = (TextView) findViewById(R.id.tv_count);

        getValue();

        //设置点击事件
        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add) {
            //加
            addNumber();

        } else {
            //减
            subNumber();
        }
    }

    private void subNumber() {
        if (value > minValue) {
            value -= 1;
        }
        setValue(value);
        if (onNumberChangeListener != null) {
            onNumberChangeListener.onNumberChange(value);
        }
    }

    private void addNumber() {
        if (value < maxValue) {
            value += 1;
        }
        setValue(value);
        if (onNumberChangeListener != null) {
            onNumberChangeListener.onNumberChange(value);
        }
    }
    //当数量发生变化的时候回调
    public interface OnNumberChangeListener {
        void onNumberChange(int value);
    }

    private OnNumberChangeListener onNumberChangeListener;

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }
}
