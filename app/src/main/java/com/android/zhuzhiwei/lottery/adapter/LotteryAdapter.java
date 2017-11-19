package com.android.zhuzhiwei.lottery.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.android.zhuzhiwei.lottery.APP;
import com.android.zhuzhiwei.lottery.R;
import com.android.zhuzhiwei.lottery.entity.LotteryBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by zhuzhiwei on 17-11-17.
 */

public class LotteryAdapter extends BaseQuickAdapter<LotteryBean, BaseViewHolder> {




    public LotteryAdapter(@LayoutRes int layoutResId, @Nullable List<LotteryBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LotteryBean item) {
        helper.setText(R.id.tv_red, APP.getAPP().getString(R.string.string_red,
                        String.valueOf(item.getRedBall_1()),
                        String.valueOf(item.getRedBall_2()),
                        String.valueOf(item.getRedBall_3()),
                        String.valueOf(item.getRedBall_4()),
                        String.valueOf(item.getRedBall_5()),
                        String.valueOf(item.getRedBall_6())
                        ));
        helper.setText(R.id.tv_basket,String.valueOf(item.getBasketBall()));
    }
}
