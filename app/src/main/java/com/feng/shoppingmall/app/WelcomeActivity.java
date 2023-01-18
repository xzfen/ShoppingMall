package com.feng.shoppingmall.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.feng.shoppingmall.R;
import com.feng.shoppingmall.utils.Constants;

public class WelcomeActivity extends Activity implements View.OnClickListener {
    private static int countTime= Constants.COUNT_DOWN_TIME;
    private TextView mTvCount;
    private boolean isSkip = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();
        //定义一个Handler的匿名子类的对象
        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 0:
                        if(!isSkip){
                            //启动主页面
                            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                            //关闭当前页面
                            finish();
                        }

                        break;
                    case 1:
                        //更新倒计时时间提示
                        mTvCount.setText(countTime + " 跳过");
                        break;
                }
            }
        };
        //开启一个线程倒计时
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (countTime > 0){
                    try {
                        Thread.sleep(1000);
                        countTime--;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(1);
                }
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    private void initView() {
        //获取倒计时TextView
        mTvCount = findViewById(R.id.tv_count);
        mTvCount.setText(countTime + " 跳过");
        //设置跳过按键的监听器
        mTvCount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        isSkip = true;
        //启动主页面
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        //关闭当前页面
        finish();
    }
}