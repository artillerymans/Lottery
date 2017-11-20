package com.android.zhuzhiwei.lottery.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.zhuzhiwei.lottery.BaseFragmentActivity;
import com.android.zhuzhiwei.lottery.adapter.FragmentViewPagerAdapter;
import com.android.zhuzhiwei.lottery.adapter.LotteryAdapter;
import com.android.zhuzhiwei.lottery.entity.LotteryBean;
import com.android.zhuzhiwei.lottery.fragment.LotteryListFragment;
import com.android.zhuzhiwei.lottery.services.LotteryService;
import com.android.zhuzhiwei.lottery.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseFragmentActivity  {

    private ViewPager mViewPager;
    private FragmentViewPagerAdapter mFragmentViewPagerAdapter;
    private LotteryListFragment mLotteryListFragment;
    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Slidr.attach(this);
        initView();
    }

    private void initView(){
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mFragmentList = new ArrayList<>();
        mLotteryListFragment = new LotteryListFragment();
        mFragmentList.add(mLotteryListFragment);
        mFragmentViewPagerAdapter = new FragmentViewPagerAdapter(this.getSupportFragmentManager(), mViewPager, mFragmentList);
    }

}
