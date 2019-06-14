package com.puy.networklibrary;

import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by puy on 2019/5/24 14:36
 */
public class NetNode {
    private String name;
    private Double delay;
    private TextView node;
    private LinearLayout linearLayout;

    public NetNode(String name, Double delay, TextView node,LinearLayout linearLayout) {
        this.name = name;
        this.delay = delay;
        this.node = node;
        this.linearLayout = linearLayout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDelay() {
        return delay;
    }

    public void setDelay(Double delay) {
        this.delay = delay;
    }

    public TextView getNode() {
        return node;
    }

    public void setNode(TextView node) {
        this.node = node;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }
}
