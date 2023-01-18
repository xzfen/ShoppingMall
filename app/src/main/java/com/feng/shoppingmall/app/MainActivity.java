package com.feng.shoppingmall.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.feng.shoppingmall.R;
import com.feng.shoppingmall.base.BaseFragment;
import com.feng.shoppingmall.community.fragment.CommunityFragment;
import com.feng.shoppingmall.home.fragment.HomeFragment;
import com.feng.shoppingmall.shoppingcart.fragment.ShoppingCartFragment;
import com.feng.shoppingmall.type.fragment.TypeFragment;
import com.feng.shoppingmall.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_type)
    RadioButton rbType;
    @BindView(R.id.rb_community)
    RadioButton rbCommunity;
    @BindView(R.id.rb_cart)
    RadioButton rbCart;
    @BindView(R.id.rb_user)
    RadioButton rbUser;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    private ArrayList<BaseFragment> fragments;
    public static final int HOME_FRAGMENT = 0;
    public static final int TYPE_FRAGMENT = 1;
    public static final int COMMUNITY_FRAGMENT = 2;
    public static final int SHOPPINGCART_FRAGMENT = 3;
    public static final int USER_FRAGMENT = 4;
    private BaseFragment mContext;
    private HomeFragment mHomeFragment;
    private TypeFragment mTypeFragment;
    private CommunityFragment mCommunityFragment;
    private ShoppingCartFragment mShoppingCartFragment;
    private UserFragment mUserFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        initListener();
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        showFragment(HOME_FRAGMENT);
                        break;
                    case R.id.rb_type:
                        showFragment(TYPE_FRAGMENT);
                        break;
                    case R.id.rb_community:
                        showFragment(COMMUNITY_FRAGMENT);
                        break;
                    case R.id.rb_cart:
                        showFragment(SHOPPINGCART_FRAGMENT);
                        break;
                    case R.id.rb_user:
                        showFragment(USER_FRAGMENT);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mTypeFragment = new TypeFragment();
        mCommunityFragment = new CommunityFragment();
        mShoppingCartFragment = new ShoppingCartFragment();
        mUserFragment = new UserFragment();
        //初始化fragment list
        fragments = new ArrayList<>();
        fragments.add(mHomeFragment);
        fragments.add(mTypeFragment);
        fragments.add(mCommunityFragment);
        fragments.add(mShoppingCartFragment);
        fragments.add(mUserFragment);

        showFragment(HOME_FRAGMENT);

        //默认设置首页
        rgMain.check(R.id.rb_home);
    }

    private void showFragment(int index) {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        //使用replace方式，秒杀里的倒计时器就无法正常计时
        //transaction.replace(R.id.frameLayout, targetFragment);
        //隐藏fragments集合中的所有Fragment
        for (int i = 0; i < fragments.size(); i++) {
            transaction.hide(fragments.get(i));
        }
        //如果指定的Fragment还没添加到transaction对象中，则先添加，再显示，否则直接显示
        if (fragments.get(index).isAdded()) {
            transaction.show(fragments.get(index));
        } else {
            transaction.add(R.id.frameLayout, fragments.get(index));
            transaction.show(fragments.get(index));
        }
        transaction.commit();
    }
}