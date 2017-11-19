package com.android.zhuzhiwei.lottery.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.android.zhuzhiwei.lottery.entity.LotteryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zhuzhiwei on 17-11-17.
 */

public class LotteryService extends Service {

    private LotteryIBind mLotteryIBind;
    private List<Integer> mThreeList;
    private List<Integer> mSixteenList;
    private Random mRandom;
    private int mMax = 6;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mLotteryIBind;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mThreeList = new ArrayList<>();
        mSixteenList = new ArrayList<>();
        mRandom = new Random();
        mSixteenList.addAll(forOneSixteen());
        mLotteryIBind = new LotteryIBind();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }


    //1到33
    private List<Integer> forOneThreeThree(){
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i< 34; i++){
            list.add(i);
        }
        return list;
    }

    //1到16
    private List<Integer> forOneSixteen(){
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i< 17; i++){
            list.add(i);
        }
        return list;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public class LotteryIBind extends Binder{

        public List<LotteryBean> machineSelection(int number){
            if(number > 0){
                List<LotteryBean> list = new ArrayList<>();
                for(int i = 0; i< number; i++){
                    LotteryBean bean = selection();
                    list.add(bean);
                }
                return list;
            }
            return null;
        }
    }


    /**
     * 核心代码  机选双色球
     * @return  机选号码组
     */
    private LotteryBean selection(){
        if(mThreeList.size() > 0){
            mThreeList.clear();
        }
        mThreeList.addAll(forOneThreeThree());
        LotteryBean bean = new LotteryBean();
        for (int i = 0; i < mMax; i++){
            int index = mRandom.nextInt(mThreeList.size());
            int number = mThreeList.remove(index);
            bean.setData(number);
        }
        int index = mRandom.nextInt(mSixteenList.size());
        int number = mSixteenList.get(index);
        bean.setData(number);
        return  bean;
    }


}
