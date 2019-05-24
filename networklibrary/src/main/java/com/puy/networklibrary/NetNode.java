package com.puy.networklibrary;

import android.widget.TextView;

/**
 * Created by puy on 2019/5/24 14:36
 */
public class NetNode {
    private String name;
    private int delay;
    private TextView node;

    public NetNode(String name, int delay, TextView node) {
        this.name = name;
        this.delay = delay;
        this.node = node;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public TextView getNode() {
        return node;
    }

    public void setNode(TextView node) {
        this.node = node;
    }
}
