package com.puy.networklibrary;

import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 设备
 * Created by puy on 2019/5/23 16:38
 */
public class NetDevice {

    private String ip;
    private String name;
    private Double delay;
    private LinearLayout linearLayout;
    private ImageView imageView;

    public NetDevice(String ip, String name, Double delay, LinearLayout linearLayout, ImageView imageView) {
        this.ip = ip;
        this.name = name;
        this.delay = delay;
        this.linearLayout = linearLayout;
        this.imageView = imageView;
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

    public Double getDelay() {
        return delay;
    }

    public void setDelay(Double delay) {
        this.delay = delay;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
