package com.puy.networklibrary;

import android.widget.LinearLayout;

/**
 * 设备
 * Created by puy on 2019/5/23 16:38
 */
public class NetDevice {

    String ip;
    String name;
    LinearLayout linearLayout;

    public NetDevice(String ip, String name, LinearLayout linearLayout) {
        this.ip = ip;
        this.name = name;
        this.linearLayout = linearLayout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
