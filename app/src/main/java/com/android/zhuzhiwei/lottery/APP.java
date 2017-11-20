package com.android.zhuzhiwei.lottery;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.litepal.LitePal;

/**
 * Created by zhuzhiwei on 17-11-17.
 */

public class APP extends Application {
    public static  APP mAPP;
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
        mAPP = this;
    }

    public static APP getAPP(){
        return mAPP;
    }
}
