package com.puy.networklibrary;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import razerdp.basepopup.BasePopupWindow;

public class DevicePopup extends BasePopupWindow {
    TextView text_device;
    TextView text_content;

    public DevicePopup(Context context) {
        super(context);
        text_device = findViewById(R.id.text_device);
        text_content = findViewById(R.id.text_content);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_device);
    }

    @Override
    protected Animation onCreateShowAnimation() {
        return getDefaultScaleAnimation(true);
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return getDefaultScaleAnimation(false);
    }

    public void setText(String name, String message) {
        text_device.setText(name);
        text_content.setText(message);
    }
}
