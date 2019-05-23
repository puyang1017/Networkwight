package com.puy.networklibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by puy on 2019/5/23 09:31
 */
public class NetworkDelayMonitor extends RelativeLayout {
    private int width;
    private int height;

    private int blue = Color.parseColor("#009FE8");
    private int red = Color.parseColor("#F14466");
    private int yellow = Color.parseColor("#EDCE2A");
    private int green = Color.parseColor("#00B374");
    private int orange = Color.parseColor("#F18E2D");
    private int black = Color.parseColor("#3A3848");
    private int text_background = Color.parseColor("#F9F9F9");

    private Paint blue_paint;
    private Paint red_paint;
    private Paint yellow_paint;
    private Paint green_paint;
    private Paint orange_paint;
    private Paint black_paint;
    private Paint red_lost_paint;
    private int line_width;

    private LinearLayout devices_ll = new LinearLayout(getContext());
    private LinearLayout center_ll = new LinearLayout(getContext());
    private ArrayList<NetDevice> netDevices = new ArrayList<>();
    private int imgHeight_base;
    private int imgHeight_node;
    private int imgWidth_node;
    private int padding;
    private ImageView image_ljb;
    private ImageView image_router;
    private ImageView image_node;

    public NetworkDelayMonitor(Context context) {
        super(context);
    }

    public NetworkDelayMonitor(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }

    private void init(Context context) {
        imgHeight_base = dp2px(32, context);
        imgHeight_node = imgHeight_base;
        imgWidth_node = dp2px(60, context);
        //内边距
        padding = dp2px(3, context);

        line_width = dp2px(1, context);
        blue_paint = new Paint();
        blue_paint.setAntiAlias(true);
        blue_paint.setStyle(Paint.Style.STROKE);
        blue_paint.setStrokeWidth(line_width);
        blue_paint.setColor(blue);

        red_paint = new Paint();
        red_paint.setAntiAlias(true);
        red_paint.setStyle(Paint.Style.STROKE);
        red_paint.setStrokeWidth(line_width);
        red_paint.setColor(red);

        yellow_paint = new Paint();
        yellow_paint.setAntiAlias(true);
        yellow_paint.setStyle(Paint.Style.STROKE);
        yellow_paint.setStrokeWidth(line_width);
        yellow_paint.setColor(yellow);

        green_paint = new Paint();
        green_paint.setAntiAlias(true);
        green_paint.setStyle(Paint.Style.STROKE);
        green_paint.setStrokeWidth(line_width);
        green_paint.setColor(green);

        orange_paint = new Paint();
        orange_paint.setAntiAlias(true);
        orange_paint.setStyle(Paint.Style.STROKE);
        orange_paint.setStrokeWidth(line_width);
        orange_paint.setColor(orange);

        black_paint = new Paint();
        black_paint.setAntiAlias(true);
        black_paint.setStyle(Paint.Style.STROKE);
        black_paint.setStrokeWidth(line_width);
        black_paint.setColor(black);

        red_lost_paint = new Paint();
        red_lost_paint.setAntiAlias(true);
        red_lost_paint.setStyle(Paint.Style.STROKE);
        red_lost_paint.setStrokeWidth(line_width);
        red_lost_paint.setColor(red);
        red_lost_paint.setPathEffect(new DashPathEffect(new float[]{dp2px(3, context), dp2px(2, context)}, 0));

        devices_ll.setOrientation(LinearLayout.HORIZONTAL);
        devices_ll.setId(R.id.NetworkDelayMonitor_devices_ll);
        devices_ll.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        center_ll.setOrientation(LinearLayout.VERTICAL);
        center_ll.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        RelativeLayout.LayoutParams layoutParams_center = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams_center.addRule(RelativeLayout.BELOW, R.id.NetworkDelayMonitor_devices_ll);

        //联机宝图标
        LinearLayout linearLayout_ljb = new LinearLayout(getContext());
        linearLayout_ljb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        ((LinearLayout.LayoutParams) linearLayout_ljb.getLayoutParams()).topMargin = dp2px(40f, getContext());
        image_ljb = new ImageView(getContext());
        image_ljb.setImageResource(R.drawable.icon_lianjibao);
        image_ljb.setBackgroundResource(R.drawable.network_background);
        image_ljb.setPadding(padding, padding, padding, padding);
        linearLayout_ljb.setGravity(Gravity.CENTER);
        linearLayout_ljb.addView(image_ljb);
        //路由图标
        LinearLayout linearLayout_router = new LinearLayout(getContext());
        linearLayout_router.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        ((LinearLayout.LayoutParams) linearLayout_router.getLayoutParams()).topMargin = dp2px(40f, getContext());
        image_router = new ImageView(getContext());
        image_router.setImageResource(R.drawable.icon_router);
        image_router.setBackgroundResource(R.drawable.network_background);
        image_router.setPadding(padding, padding, padding, padding);
        linearLayout_router.setGravity(Gravity.CENTER);
        linearLayout_router.addView(image_router);

        //节点图标
        LinearLayout linearLayout_node = new LinearLayout(getContext());
        linearLayout_node.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        ((LinearLayout.LayoutParams) linearLayout_node.getLayoutParams()).topMargin = dp2px(40f, getContext());
        image_node = new ImageView(getContext());
        image_node.setImageResource(R.drawable.icon_node);
        image_node.setBackgroundResource(R.drawable.network_background);
        image_node.setPadding(padding, padding, padding, padding);
        linearLayout_node.setGravity(Gravity.CENTER);
        linearLayout_node.addView(image_node);

        center_ll.addView(linearLayout_ljb);
        center_ll.addView(linearLayout_router);
        center_ll.addView(linearLayout_node);
        this.addView(devices_ll);
        this.addView(center_ll, layoutParams_center);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public int dp2px(float dp, Context mContext) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 添加设备
     *
     * @param resId 设备图片资源
     * @param name  设备名称
     * @param ip    设备ip
     */
    public void addDevice(@DrawableRes int resId, final String name, final String ip) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        ImageView device = new ImageView(getContext());
        device.setImageResource(resId);
        device.setBackgroundResource(R.drawable.network_background);
        device.setPadding(padding, padding, padding, padding);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        linearLayout.addView(device);
        linearLayout.setGravity(Gravity.CENTER);
        devices_ll.addView(linearLayout);
        netDevices.add(new NetDevice(ip, name, linearLayout));
        device.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), name + " " + ip, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 移除设备
     *
     * @param name 设备名称
     */
    public void removeDevice(String name) {
        for (NetDevice netDevice : netDevices) {
            if (netDevice.getName().equals(name)) {
                devices_ll.removeView(netDevice.getLinearLayout());
            }
        }
    }

    /**
     * 获取设备
     *
     * @return
     */
    public ArrayList<NetDevice> getNetDevices() {
        return netDevices;
    }
}
