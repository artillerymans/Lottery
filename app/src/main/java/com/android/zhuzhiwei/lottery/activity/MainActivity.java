package com.android.zhuzhiwei.lottery.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.zhuzhiwei.lottery.BaseFragmentActivity;
import com.android.zhuzhiwei.lottery.adapter.FragmentViewPagerAdapter;
import com.android.zhuzhiwei.lottery.adapter.LotteryAdapter;
import com.android.zhuzhiwei.lottery.entity.LotteryBean;
import com.android.zhuzhiwei.lottery.fragment.LotteryListFragment;
import com.android.zhuzhiwei.lottery.fragment.LotteryOfficialFragment;
import com.android.zhuzhiwei.lottery.services.LotteryService;
import com.android.zhuzhiwei.lottery.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private FragmentViewPagerAdapter mFragmentViewPagerAdapter;
    private LotteryListFragment mLotteryListFragment;
    private LotteryOfficialFragment mLotteryOfficialFragment;
    private List<Fragment> mFragmentList;
    private FloatingActionButton mFloatingActionButton;
    private SlidrInterface mSlidrInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSlidrInterface = Slidr.attach(this);
        initView();
    }

    private void initView(){
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.main_clear);
        mFloatingActionButton.setOnClickListener(this);
        mFragmentList = new ArrayList<>();
        mLotteryListFragment = new LotteryListFragment();
        mLotteryOfficialFragment = new LotteryOfficialFragment();
        mFragmentList.add(mLotteryListFragment);
        mFragmentList.add(mLotteryOfficialFragment);
        mFragmentViewPagerAdapter = new FragmentViewPagerAdapter(this.getSupportFragmentManager(), mViewPager, mFragmentList);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        DataSupport.deleteAll(LotteryBean.class);
        if(mLotteryListFragment != null){
            mLotteryListFragment.findLotteryData();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position == 1){   //屏蔽滑动第二屏时，出现右划退出
            mSlidrInterface.lock();
            mFloatingActionButton.setVisibility(View.GONE);
        }else {
            mSlidrInterface.unlock();
            mFloatingActionButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
