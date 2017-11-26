/**
  * Copyright 2017 bejson.com 
  */
package com.android.zhuzhiwei.lottery.entity;
/**
 * Created by zhuzhiwei on 17-11-26.
 */
public class Data {

    private String expect;
    private String opencode;
    private String opentime;
    private int opentimestamp;
    public void setExpect(String expect) {
         this.expect = expect;
     }
     public String getExpect() {
         return expect;
     }

    public void setOpencode(String opencode) {
         this.opencode = opencode;
     }
     public String getOpencode() {
         return opencode;
     }

    public void setOpentime(String opentime) {
         this.opentime = opentime;
     }
     public String getOpentime() {
         return opentime;
     }

    public void setOpentimestamp(int opentimestamp) {
         this.opentimestamp = opentimestamp;
     }
     public int getOpentimestamp() {
         return opentimestamp;
     }

    @Override
    public String toString() {
        return "Data{" +
                "expect='" + expect + '\'' +
                ", opencode='" + opencode + '\'' +
                ", opentime='" + opentime + '\'' +
                ", opentimestamp=" + opentimestamp +
                '}';
    }
}