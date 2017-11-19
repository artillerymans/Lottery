package com.android.zhuzhiwei.lottery.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.zhuzhiwei.lottery.adapter.LotteryAdapter;
import com.android.zhuzhiwei.lottery.entity.LotteryBean;
import com.android.zhuzhiwei.lottery.services.LotteryService;
import com.android.zhuzhiwei.lottery.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private LotteryService.LotteryIBind mLotteryIBind;
    private RecyclerView mRecyclerView;
    private LotteryAdapter mLotteryAdapter;
    private List<LotteryBean> mList;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Slidr.attach(this);
        initView();
        bindService();
    }


    private void initView(){
        mList = new ArrayList<>();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.main_swiperefreshlayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mLotteryAdapter = new LotteryAdapter(R.layout.layout_lottery_item, mList);
        mLotteryAdapter.setUpFetchEnable(true);
        mLotteryAdapter.setEnableLoadMore(false);
        mRecyclerView.setAdapter(mLotteryAdapter);
        mLotteryAdapter.setUpFetchListener(mUpFetchListener);
    }


    private BaseQuickAdapter.UpFetchListener mUpFetchListener = new BaseQuickAdapter.UpFetchListener() {
        @Override
        public void onUpFetch() {

        }
    };


    private void bindService(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, LotteryService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mLotteryIBind = (LotteryService.LotteryIBind) service;
           /* List<LotteryBean> list = mLotteryIBind.machineSelection(5);
            if(mLotteryAdapter != null){
                mLotteryAdapter.addData(list);
            }*/
            Logger.d("onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Logger.d("onServiceDisconnected");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }

    @Override
    public void onRefresh() {
        if(mLotteryIBind != null){
            List<LotteryBean> list = mLotteryIBind.machineSelection(5);
            mLotteryAdapter.addData(0, list);
            mSwipeRefreshLayout.setRefreshing(false);
            mRecyclerView.scrollToPosition(0);
        }
    }
}
