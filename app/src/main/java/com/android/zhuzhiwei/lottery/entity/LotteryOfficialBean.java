/**
 * Copyright 2017 bejson.com
 */
package com.android.zhuzhiwei.lottery.entity;
import java.util.List;

/**
 * Created by zhuzhiwei on 17-11-26.
 */
public class LotteryOfficialBean {

    private int rows;
    private String code;
    private String info;
    private List<Data> data;
    public void setRows(int rows) {
        this.rows = rows;
    }
    public int getRows() {
        return rows;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "LotteryOfficialBean{" +
                "rows=" + rows +
                ", code='" + code + '\'' +
                ", info='" + info + '\'' +
                ", data=" + data +
                '}';
    }
}