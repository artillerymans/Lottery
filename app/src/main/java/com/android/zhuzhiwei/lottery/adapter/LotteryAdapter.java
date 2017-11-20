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
       /* helper.setText(R.id.tv_red, APP.getAPP().getString(R.string.string_red,
                        String.valueOf(item.getRedBall_1()),
                        String.valueOf(item.getRedBall_2()),
                        String.valueOf(item.getRedBall_3()),
                        String.valueOf(item.getRedBall_4()),
                        String.valueOf(item.getRedBall_5()),
                        String.valueOf(item.getRedBall_6())
                        ));*/

        helper.setText(R.id.list_item_red1, item.getRedBall_1() < 10 ? ("0" + item.getRedBall_1()) : String.valueOf(item.getRedBall_1()));
        helper.setText(R.id.list_item_red2, item.getRedBall_2() < 10 ? ("0" + item.getRedBall_2()) : String.valueOf(item.getRedBall_2()));
        helper.setText(R.id.list_item_red3, item.getRedBall_3() < 10 ? ("0" + item.getRedBall_3()) : String.valueOf(item.getRedBall_3()));
        helper.setText(R.id.list_item_red4, item.getRedBall_4() < 10 ? ("0" + item.getRedBall_4()) : String.valueOf(item.getRedBall_4()));
        helper.setText(R.id.list_item_red5, item.getRedBall_5() < 10 ? ("0" + item.getRedBall_5()) : String.valueOf(item.getRedBall_5()));
        helper.setText(R.id.list_item_red6, item.getRedBall_6() < 10 ? ("0" + item.getRedBall_6()) : String.valueOf(item.getRedBall_6()));
        helper.setText(R.id.list_item_red7, item.getBasketBall() < 10 ? ("0" + item.getBasketBall()) : String.valueOf(item.getBasketBall()));
    }
}
