package com.android.zhuzhiwei.lottery.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.zhuzhiwei.lottery.BaseFragment;
import com.android.zhuzhiwei.lottery.R;
import com.android.zhuzhiwei.lottery.activity.MainActivity;
import com.android.zhuzhiwei.lottery.entity.Data;
import com.android.zhuzhiwei.lottery.entity.LotteryOfficialBean;
import com.android.zhuzhiwei.lottery.utils.Constant;
import com.android.zhuzhiwei.lottery.utils.LotteryUtils;
import com.android.zhuzhiwei.lottery.utils.RxTextTool;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.lang.ref.WeakReference;

import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhiwei_zhu on 17-11-26.
 */

public class LotteryOfficialFragment extends BaseFragment implements View.OnClickListener {

    private View mView;
    public TextView mTvLotteryCode,mTvUpTime;
    public ImageView mIvRefresh;
    private Animation mCircle_anim;

  /*  private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(Constant.HANDLER_WHAT_LASTUPTIME == msg.what){
                if(mIvRefresh != null){
                    mIvRefresh.clearAnimation();
                }
                LotteryOfficialBean bean = (LotteryOfficialBean) msg.obj;
                Toasty.success(mActivity, getString(R.string.success_up), Toast.LENGTH_SHORT, true).show();
                mTvUpTime.setText(getString(R.string.uptime, LotteryUtils.millis2String(System.currentTimeMillis())));
                Data d = bean.getData().get(0);
                String code = d.getOpencode();
                int index_add = code.lastIndexOf(getString(R.string.plus));
                String red_code = code.substring(0, index_add);
                String blue_code = code.substring(index_add , code.length());
                RxTextTool.getBuilder(getString(R.string.expect, d.getExpect())).setBold().setAlign(Layout.Alignment.ALIGN_CENTER)
                        .append(red_code).setForegroundColor(Color.RED).append(blue_code).setForegroundColor(Color.BLUE).into(mTvLotteryCode);
            }else if(Constant.HANDLER_WHAT_UPFAIL == msg.what){
                Toasty.error(mActivity, getString(R.string.error_up), Toast.LENGTH_SHORT, true).show();
                if(mIvRefresh != null){
                    mIvRefresh.clearAnimation();
                }
            }
        }
    };*/


    /**
     * Instances of static inner classes do not hold an implicit
     * reference to their outer class.
     */
    private static class MyHandler extends Handler {
        private final LotteryOfficialFragment mFragment;

        public MyHandler(LotteryOfficialFragment fragment) {
            mFragment = fragment;
        }

        @Override
        public void handleMessage(Message msg) {
            if (mFragment != null) {
                if(Constant.HANDLER_WHAT_LASTUPTIME == msg.what){
                    if(mFragment.mIvRefresh != null){
                        mFragment.mIvRefresh.clearAnimation();
                    }
                    LotteryOfficialBean bean = (LotteryOfficialBean) msg.obj;
                    Toasty.success(mFragment.getContext(), mFragment.getContext().getString(R.string.success_up), Toast.LENGTH_SHORT, true).show();
                    mFragment.mTvUpTime.setText(mFragment.getContext().getString(R.string.uptime, LotteryUtils.millis2String(System.currentTimeMillis())));
                    Data d = bean.getData().get(0);
                    String code = d.getOpencode();
                    int index_add = code.lastIndexOf(mFragment.getContext().getString(R.string.plus));
                    String red_code = code.substring(0, index_add);
                    String blue_code = code.substring(index_add , code.length());
                    RxTextTool.getBuilder(mFragment.getContext().getString(R.string.expect, d.getExpect())).setBold().setAlign(Layout.Alignment.ALIGN_CENTER)
                            .append(red_code).setForegroundColor(Color.RED).append(blue_code).setForegroundColor(Color.BLUE).into(mFragment.mTvLotteryCode);
                }else if(Constant.HANDLER_WHAT_UPFAIL == msg.what){
                    Toasty.error(mFragment.getContext(), mFragment.getContext().getString(R.string.error_up), Toast.LENGTH_SHORT, true).show();
                    if(mFragment.mIvRefresh != null){
                        mFragment.mIvRefresh.clearAnimation();
                    }
                }
            }
        }
    }

    private final MyHandler mHandler = new MyHandler(this);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_lotteryofficaial, null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mTvLotteryCode = (TextView) mView.findViewById(R.id.fragment_officaial_tv_lottery_code);
        mTvUpTime = (TextView) mView.findViewById(R.id.fragment_officaial_tv_last_uptime);
        mIvRefresh = (ImageView) mView.findViewById(R.id.fragment_officaial_iv_lottery_up);
        mIvRefresh.setOnClickListener(this);
        //定义旋转动画
        mCircle_anim = AnimationUtils.loadAnimation(mActivity , R.anim.anim_round_rotate);
        LinearInterpolator interpolator = new LinearInterpolator();  //设置匀速旋转，在xml文件中设置会出现卡顿
        mCircle_anim.setInterpolator(interpolator);

        refreshLastLotteryCode();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_officaial_iv_lottery_up:
                refreshLastLotteryCode();
                break;
        }
    }


    /**
     * 获取最近5期双色球开奖号码
     */
    private void refreshLastLotteryCode(){
        if(mCircle_anim != null){
            mIvRefresh.startAnimation(mCircle_anim);
        }
        OkHttpClient client = new OkHttpClient();
        //构造Request对象
        //采用建造者模式，链式调用指明进行Get请求,传入Get的请求地址
        Request request = new Request.Builder().get().url(Constant.URL_SSQ_DEFUAL).build();
        Call call = client.newCall(request);
        //异步调用并设置回调函数
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(Constant.HANDLER_WHAT_UPFAIL);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();
                LotteryOfficialBean lotteryOfficialBean = JSON.parseObject(responseStr, LotteryOfficialBean.class);
                Logger.d(lotteryOfficialBean);
                Message message = mHandler.obtainMessage();
                message.what = Constant.HANDLER_WHAT_LASTUPTIME;
                message.obj = lotteryOfficialBean;
                mHandler.sendMessage(message);
            }
        });
    }
}
