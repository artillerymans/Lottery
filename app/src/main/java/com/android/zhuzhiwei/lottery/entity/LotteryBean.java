package com.android.zhuzhiwei.lottery.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhuzhiwei on 17-11-17.
 */

public class LotteryBean implements Parcelable {

    private int RedBall_1 = -1;
    private int RedBall_2 = -1;
    private int RedBall_3 = -1;
    private int RedBall_4 = -1;
    private int RedBall_5 = -1;
    private int RedBall_6 = -1;
    private int BasketBall = -1;
    protected int index = 1;

    public LotteryBean() {
    }

    public LotteryBean(int redBall_1, int redBall_2, int redBall_3, int redBall_4, int redBall_5, int redBall_6, int basketBall) {
        RedBall_1 = redBall_1;
        RedBall_2 = redBall_2;
        RedBall_3 = redBall_3;
        RedBall_4 = redBall_4;
        RedBall_5 = redBall_5;
        RedBall_6 = redBall_6;
        BasketBall = basketBall;
    }


    public int getRedBall_1() {
        return RedBall_1;
    }

    public void setRedBall_1(int redBall_1) {
        RedBall_1 = redBall_1;
    }

    public int getRedBall_2() {
        return RedBall_2;
    }

    public void setRedBall_2(int redBall_2) {
        RedBall_2 = redBall_2;
    }

    public int getRedBall_3() {
        return RedBall_3;
    }

    public void setRedBall_3(int redBall_3) {
        RedBall_3 = redBall_3;
    }

    public int getRedBall_4() {
        return RedBall_4;
    }

    public void setRedBall_4(int redBall_4) {
        RedBall_4 = redBall_4;
    }

    public int getRedBall_5() {
        return RedBall_5;
    }

    public void setRedBall_5(int redBall_5) {
        RedBall_5 = redBall_5;
    }

    public int getRedBall_6() {
        return RedBall_6;
    }

    public void setRedBall_6(int redBall_6) {
        RedBall_6 = redBall_6;
    }

    public int getBasketBall() {
        return BasketBall;
    }

    public void setBasketBall(int basketBall) {
        BasketBall = basketBall;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.RedBall_1);
        dest.writeInt(this.RedBall_2);
        dest.writeInt(this.RedBall_3);
        dest.writeInt(this.RedBall_4);
        dest.writeInt(this.RedBall_5);
        dest.writeInt(this.RedBall_6);
        dest.writeInt(this.BasketBall);
    }

    protected LotteryBean(Parcel in) {
        this.RedBall_1 = in.readInt();
        this.RedBall_2 = in.readInt();
        this.RedBall_3 = in.readInt();
        this.RedBall_4 = in.readInt();
        this.RedBall_5 = in.readInt();
        this.RedBall_6 = in.readInt();
        this.BasketBall = in.readInt();
    }

    public static final Parcelable.Creator<LotteryBean> CREATOR = new Parcelable.Creator<LotteryBean>() {
        @Override
        public LotteryBean createFromParcel(Parcel source) {
            return new LotteryBean(source);
        }

        @Override
        public LotteryBean[] newArray(int size) {
            return new LotteryBean[size];
        }
    };

    public void setData(int number){
        switch (index){
            case 1:
                setRedBall_1(number);
                index +=1;
                break;
            case 2:
                setRedBall_2(number);
                index +=1;
                break;
            case 3:
                setRedBall_3(number);
                index +=1;
                break;
            case 4:
                setRedBall_4(number);
                index +=1;
                break;
            case 5:
                setRedBall_5(number);
                index +=1;
                break;
            case 6:
                setRedBall_6(number);
                index +=1;
                break;
            case 7:
                setBasketBall(number);
                index +=1;
                break;
        }
    }


    @Override
    public String toString() {
        return "LotteryBean{" +
                "RedBall_1=" + RedBall_1 +
                ", RedBall_2=" + RedBall_2 +
                ", RedBall_3=" + RedBall_3 +
                ", RedBall_4=" + RedBall_4 +
                ", RedBall_5=" + RedBall_5 +
                ", RedBall_6=" + RedBall_6 +
                ", BasketBall=" + BasketBall +
                '}';
    }


}
