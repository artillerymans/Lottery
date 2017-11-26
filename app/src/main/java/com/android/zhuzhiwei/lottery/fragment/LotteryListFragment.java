package com.android.zhuzhiwei.lottery.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.zhuzhiwei.lottery.BaseFragment;
import com.android.zhuzhiwei.lottery.R;
import com.android.zhuzhiwei.lottery.activity.MainActivity;
import com.android.zhuzhiwei.lottery.adapter.LotteryAdapter;
import com.android.zhuzhiwei.lottery.entity.LotteryBean;
import com.android.zhuzhiwei.lottery.services.LotteryService;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuzhiwei on 17-11-18.
 */

public class LotteryListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private View mView;
    private RecyclerView mRecyclerView;
    private TextView mTvTip;
    private LotteryAdapter mLotteryAdapter;
    private List<LotteryBean> mList;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LotteryService.LotteryIBind mLotteryIBind;

    public LotteryListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_lotterylist, null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        findLotteryData();
        bindService();
    }

    private void initView(){
        mList = new ArrayList<>();
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.fragment_list_swiperefreshlayout);
        mTvTip = (TextView) mView.findViewById(R.id.fragment_list_tvtip);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.fragment_list_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
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

    public void findLotteryData(){
        if(mList != null && mList.size() > 0){
            mList.clear();
        }
        List<LotteryBean> list = DataSupport.findAll(LotteryBean.class);
        mList.addAll(list);
        if(mList.size() == 0 ){
            mTvTip.setText(R.string.refresh_one);
        }else{
            mTvTip.setText(R.string.title);
        }
        if(mLotteryAdapter !=null){
            mLotteryAdapter.notifyDataSetChanged();
        }
    }

    private void bindService(){
        Intent intent = new Intent();
        intent.setClass(getActivity(), LotteryService.class);
        getActivity().bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }



    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mLotteryIBind = (LotteryService.LotteryIBind) service;
            Logger.d("onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Logger.d("onServiceDisconnected");
        }
    };

    @Override
    public void onRefresh() {
        if(mLotteryIBind != null){
            List<LotteryBean> list = mLotteryIBind.machineSelection(5);
            mLotteryAdapter.addData(0, list);
            mSwipeRefreshLayout.setRefreshing(false);
            mRecyclerView.scrollToPosition(0);
            mTvTip.setText(R.string.title);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unbindService(mServiceConnection);
    }


}
