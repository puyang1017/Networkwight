package com.puy.networklibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import razerdp.basepopup.BasePopupWindow;

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
    private int bg_line_color = Color.parseColor("#E6E6E7");
    private int bg_text_color = Color.parseColor("#ACAFB8");

    private Paint blue_paint;
    private Paint red_paint;
    private Paint yellow_paint;
    private Paint green_paint;
    private Paint orange_paint;
    private Paint black_paint;
    private Paint red_lost_paint;
    private Paint bg_line_paint;
    private int line_width;

    private LinearLayout devices_ll = new LinearLayout(getContext());
    private LinearLayout center_ll = new LinearLayout(getContext());
    private LinearLayout node_ll = new LinearLayout(getContext());
    private CopyOnWriteArrayList<NetDevice> netDevices = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<NetNode> netNodes = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<DevicePopup> devicePopups = new CopyOnWriteArrayList<>();
    private int imgHeight_base;
    private int imgWidth_base;
    private int imgHeight_service;
    private int imgWidth_service;
    private int imgHeight_node;
    private int imgWidth_node;
    private int padding;
    private ImageView image_ljb;
    private ImageView image_router;
    private ImageView image_node;
    private LinearLayout linearLayout_service;
    //屏幕位置差值
    private int mX;
    private int mY;
    private int line_hight;
    private int ms_hight;
    private int textSize = dp2px(7, getContext());
    private int bg_textSize = dp2px(10, getContext());

    //联机宝延迟
    private Double ljbDelay = -1d;
    //路由器延迟
    private Double routerDelay = -1d;

    int[] local_ljb;
    int[] local_router;
    int[] local_service;

    enum Type {
        DEVICE,
        LOCAL,
        OPERATOR,
        NODE
    }

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
        imgWidth_base = imgHeight_base;
        imgHeight_service = imgHeight_base;
        imgWidth_service = dp2px(60, context);
        imgHeight_node = dp2px(26, context);
        imgWidth_node = dp2px(20, context);
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

        bg_line_paint = new Paint();
        bg_line_paint.setAntiAlias(true);
        bg_line_paint.setStyle(Paint.Style.STROKE);
        bg_line_paint.setStrokeWidth(line_width);
        bg_line_paint.setColor(bg_line_color);
        bg_line_paint.setPathEffect(new DashPathEffect(new float[]{dp2px(3, context), dp2px(2, context)}, 0));

        devices_ll.setOrientation(LinearLayout.HORIZONTAL);
        devices_ll.setId(R.id.NetworkDelayMonitor_devices_ll);
        devices_ll.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        center_ll.setOrientation(LinearLayout.VERTICAL);
        center_ll.setId(R.id.NetworkDelayMonitor_center_ll);
        center_ll.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        node_ll.setOrientation(LinearLayout.HORIZONTAL);
        node_ll.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        RelativeLayout.LayoutParams layoutParams_center = new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams_center.addRule(RelativeLayout.BELOW, R.id.NetworkDelayMonitor_devices_ll);

        RelativeLayout.LayoutParams layoutParams_node = new LayoutParams(LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams_node.addRule(RelativeLayout.BELOW, R.id.NetworkDelayMonitor_center_ll);
        layoutParams_node.topMargin = dp2px(90f, getContext());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(imgWidth_base, imgHeight_base);
        //联机宝图标
        LinearLayout linearLayout_ljb = new LinearLayout(getContext());
        linearLayout_ljb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        ((LinearLayout.LayoutParams) linearLayout_ljb.getLayoutParams()).topMargin = dp2px(40f, getContext());
        image_ljb = new ImageView(getContext());
        image_ljb.setImageResource(R.drawable.icon_lianjibao);
        image_ljb.setBackgroundResource(R.drawable.network_background);
        image_ljb.setPadding(padding, padding, padding, padding);
        image_ljb.setLayoutParams(params);
        linearLayout_ljb.setGravity(Gravity.CENTER);
        linearLayout_ljb.addView(image_ljb);
        image_ljb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ljbDelay != -1) {
                    final DevicePopup devicePopup = new DevicePopup(getContext());
                    devicePopup.setText("延迟", ljbDelay + "ms");
                    devicePopup.setBackgroundColor(Color.TRANSPARENT);
                    devicePopup.setPopupGravity(Gravity.CENTER_HORIZONTAL);
                    devicePopup.setOffsetY(dp2px(4, getContext()));
                    devicePopup.showPopupWindow(v);
                    devicePopups.add(devicePopup);
                    devicePopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            devicePopups.remove(devicePopup);
                        }
                    });
                }
            }
        });
        //路由图标
        LinearLayout linearLayout_router = new LinearLayout(getContext());
        linearLayout_router.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        ((LinearLayout.LayoutParams) linearLayout_router.getLayoutParams()).topMargin = dp2px(40f, getContext());
        image_router = new ImageView(getContext());
        image_router.setImageResource(R.drawable.icon_router);
        image_router.setBackgroundResource(R.drawable.network_background);
        image_router.setPadding(padding, padding, padding, padding);
        image_router.setLayoutParams(params);
        linearLayout_router.setGravity(Gravity.CENTER);
        linearLayout_router.addView(image_router);
        image_router.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (routerDelay != -1) {
                    final DevicePopup devicePopup = new DevicePopup(getContext());
                    devicePopup.setText("延迟", routerDelay + "ms");
                    devicePopup.setBackgroundColor(Color.TRANSPARENT);
                    devicePopup.setPopupGravity(Gravity.CENTER_HORIZONTAL);
                    devicePopup.setOffsetY(dp2px(4, getContext()));
                    devicePopup.showPopupWindow(v);
                    devicePopups.add(devicePopup);
                    devicePopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            devicePopups.remove(devicePopup);
                        }
                    });
                }
            }
        });
        //服务图标
        linearLayout_service = new LinearLayout(getContext());
        linearLayout_service.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        ((LinearLayout.LayoutParams) linearLayout_service.getLayoutParams()).topMargin = dp2px(40f, getContext());
        image_node = new ImageView(getContext());
        LinearLayout.LayoutParams params_node = new LinearLayout.LayoutParams(imgWidth_service, imgHeight_service);
        image_node.setLayoutParams(params_node);
        image_node.setImageResource(R.drawable.icon_node);
        image_node.setBackgroundResource(R.drawable.network_background);
        image_node.setPadding(padding, padding, padding, padding);
        linearLayout_service.setGravity(Gravity.CENTER);
        linearLayout_service.addView(image_node);

        LinearLayout linearLayout_node = new LinearLayout(getContext());
        linearLayout_node.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        linearLayout_node.setGravity(Gravity.CENTER);
        linearLayout_node.addView(node_ll);

        center_ll.addView(linearLayout_ljb);
        center_ll.addView(linearLayout_router);
        center_ll.addView(linearLayout_service);
        this.addView(devices_ll);
        this.addView(center_ll, layoutParams_center);
        this.addView(linearLayout_node, layoutParams_node);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mX = getLeft();
        int[] mlocal_ljb = getLocation(image_ljb);
        int[] mlocal_router = getLocation(image_router);
        int[] mlocal_service = getLocation(image_node);
        if (mlocal_ljb[0] < 0 || mlocal_router[0] < 0 || mlocal_service[0] < 0) {
            return;
        }
        if (mlocal_ljb[1] < 0 || mlocal_router[1] < 0 || mlocal_service[1] < 0) {
            return;
        }
        local_ljb = mlocal_ljb;
        local_router = mlocal_router;
        local_service = mlocal_service;
        System.out.println("NetworkDelayMonitor mX:" + mX);
        System.out.println("NetworkDelayMonitor local_ljb " + local_ljb[0] + " " + local_ljb[1]);
        System.out.println("NetworkDelayMonitor local_router " + local_router[0] + " " + local_router[1]);
        System.out.println("NetworkDelayMonitor local_service " + local_service[0] + " " + local_service[1]);
        int index = netDevices.size() / 2;
        int line_h = imgHeight_base / (netDevices.size() + 1);
        line_hight = 0;
        //绘制背景虚线和文字
        int padding_left = dp2px(10, getContext());
        for (int i = 0; i < 4; i++) {
            Path bg_line_path = new Path();
            bg_line_path.moveTo(padding_left, imgHeight_base / 2f + dp2px(72, getContext()) * i);
            bg_line_path.lineTo(padding_left + width, imgHeight_base / 2f + dp2px(72, getContext()) * i);
            canvas.drawPath(bg_line_path, bg_line_paint);

            Paint bg_textPaint = new Paint();
            bg_textPaint.setAntiAlias(true);
            bg_textPaint.setStyle(Paint.Style.FILL);
            bg_textPaint.setStrokeWidth(12);
            bg_textPaint.setTextSize(bg_textSize);
            bg_textPaint.setTextAlign(Paint.Align.CENTER);
            bg_textPaint.setColor(bg_text_color);
            switch (i) {
                case 0:
                    canvas.drawText("设", padding_left + bg_textSize / 2f, imgHeight_base / 2f + dp2px(7, getContext()) + bg_textSize, bg_textPaint);
                    canvas.drawText("备", padding_left + bg_textSize / 2f, imgHeight_base / 2f + dp2px(7, getContext()) + bg_textSize * 2.5f, bg_textPaint);
                    canvas.drawText("连", padding_left + bg_textSize / 2f, imgHeight_base / 2f + dp2px(7, getContext()) + bg_textSize * 4f, bg_textPaint);
                    canvas.drawText("接", padding_left + bg_textSize / 2f, imgHeight_base / 2f + dp2px(7, getContext()) + bg_textSize * 5.5f, bg_textPaint);
                    break;
                case 1:
                    canvas.drawText("本", padding_left + bg_textSize / 2f, imgHeight_base / 2f + dp2px(7, getContext()) + dp2px(72, getContext()) * i + bg_textSize, bg_textPaint);
                    canvas.drawText("地", padding_left + bg_textSize / 2f, imgHeight_base / 2f + dp2px(7, getContext()) + dp2px(72, getContext()) * i + bg_textSize * 2.5f, bg_textPaint);
                    canvas.drawText("网", padding_left + bg_textSize / 2f, imgHeight_base / 2f + dp2px(7, getContext()) + dp2px(72, getContext()) * i + bg_textSize * 4f, bg_textPaint);
                    canvas.drawText("络", padding_left + bg_textSize / 2f, imgHeight_base / 2f + dp2px(7, getContext()) + dp2px(72, getContext()) * i + bg_textSize * 5.5f, bg_textPaint);
                    break;
                case 2:
                    canvas.drawText("运", padding_left + bg_textSize / 2f, imgHeight_base / 2f + dp2px(14, getContext()) + dp2px(72, getContext()) * i + bg_textSize, bg_textPaint);
                    canvas.drawText("营", padding_left + bg_textSize / 2f, imgHeight_base / 2f + dp2px(14, getContext()) + dp2px(72, getContext()) * i + bg_textSize * 2.5f, bg_textPaint);
                    canvas.drawText("商", padding_left + bg_textSize / 2f, imgHeight_base / 2f + dp2px(14, getContext()) + dp2px(72, getContext()) * i + bg_textSize * 4f, bg_textPaint);
                    break;
                case 3:
                    canvas.drawText("加", padding_left + bg_textSize / 2f, imgHeight_base / 2f + dp2px(7, getContext()) + dp2px(72, getContext()) * i + bg_textSize, bg_textPaint);
                    canvas.drawText("速", padding_left + bg_textSize / 2f, imgHeight_base / 2f + dp2px(7, getContext()) + dp2px(72, getContext()) * i + bg_textSize * 2.5f, bg_textPaint);
                    canvas.drawText("网", padding_left + bg_textSize / 2f, imgHeight_base / 2f + dp2px(7, getContext()) + dp2px(72, getContext()) * i + bg_textSize * 4f, bg_textPaint);
                    canvas.drawText("络", padding_left + bg_textSize / 2f, imgHeight_base / 2f + dp2px(7, getContext()) + dp2px(72, getContext()) * i + bg_textSize * 5.5f, bg_textPaint);
                    break;
            }
        }

        //绘制设备
        for (int i = 0; i < netDevices.size(); i++) {
            NetDevice netDevice = netDevices.get(i);
            int[] local_device = getLocation(netDevice.getImageView());
            int x = local_device[0];
            int y = local_device[1];
            mY = y;
            if (i < index) {
                line_hight = line_hight - line_h;
            } else if (i > index) {
                line_hight = line_hight + line_h;
            } else {
                if (isOdd(netDevices.size())) {
                    line_hight = line_hight - line_h;
                }
            }
            Path path = new Path();
            path.moveTo(x + imgHeight_base / 2f - mX, 0);
            path.lineTo(x + imgHeight_base / 2f - mX, imgHeight_base + dp2px(35, getContext()) + line_hight);
            path.lineTo(local_ljb[0] + imgHeight_base * (i + 1) / (netDevices.size() + 1f) - mX, imgHeight_base + dp2px(35, getContext()) + line_hight);
            path.lineTo(local_ljb[0] + imgHeight_base * (i + 1) / (netDevices.size() + 1f) - mX, local_ljb[1] - mY);
            setMyLineColor(canvas, path, netDevice.getDelay(), Type.DEVICE);
            setMyText(canvas, netDevice.getDelay(), (int) (x + imgHeight_base / 2f - mX),
                    imgHeight_base + dp2px(15, getContext()), Type.DEVICE);
        }

        //绘制节点
        index = netNodes.size() / 2;
        line_h = imgWidth_service / (netNodes.size() + 1);
        line_hight = 0;
        ms_hight = 0;
        for (int i = 0; i < netNodes.size(); i++) {
            NetNode netNode = netNodes.get(i);
            int[] local_node = getLocation(netNode.getNode());
            int x = local_node[0];
            int y = local_node[1];
            if (i < index) {
                line_hight = line_hight + line_h;
            } else if (i > index) {
                line_hight = line_hight - line_h;
            } else {
                if (isOdd(netNodes.size())) {
                    line_hight = line_hight + line_h;
                }
            }
            if (isOdd(i)) {
                ms_hight = dp2px(70, getContext());
            } else {
                ms_hight = dp2px(55, getContext());
            }
            Path path = new Path();
            path.moveTo(local_service[0] + imgWidth_service * (i + 1) / (netNodes.size() + 1f) - mX, local_service[1] + imgHeight_service - mY);
            path.lineTo(local_service[0] + imgWidth_service * (i + 1) / (netNodes.size() + 1f) - mX, local_service[1] + imgHeight_service - mY + dp2px(10, getContext()) + line_hight);
            path.lineTo(x + imgWidth_node / 2 - mX, local_service[1] + imgHeight_service - mY + dp2px(10, getContext()) + line_hight);
            path.lineTo(x + imgWidth_node / 2 - mX, y - mY);
            setMyLineColor(canvas, path, netNode.getDelay(), Type.NODE);
            setMyText(canvas, netNode.getDelay(), x + imgWidth_node / 2 - mX,
                    local_service[1] + imgHeight_service - mY + ms_hight, Type.NODE);
        }


        if (ljbDelay != -1) {
            Path path = new Path();
            path.moveTo(local_ljb[0] + imgWidth_base / 2 - mX, local_ljb[1] + imgWidth_base - mY);
            path.lineTo(local_router[0] + imgWidth_base / 2 - mX, local_router[1] + imgWidth_base - mY);
            setMyLineColor(canvas, path, ljbDelay, Type.LOCAL);
            setMyText(canvas, ljbDelay, local_ljb[0] + imgWidth_base / 2 - mX, local_ljb[1] + imgWidth_base - mY + (local_router[1] - local_ljb[1] - imgWidth_base) / 2, Type.LOCAL);
        }

        if (routerDelay != -1) {
            Path path = new Path();
            path.moveTo(local_router[0] + imgWidth_base / 2 - mX, local_router[1] + imgWidth_base - mY);
            path.lineTo(local_service[0] + imgWidth_service / 2 - mX, local_service[1] + imgHeight_service - mY);
            setMyLineColor(canvas, path, routerDelay, Type.OPERATOR);
            setMyText(canvas, routerDelay, local_router[0] + imgWidth_base / 2 - mX, local_router[1] + imgWidth_base - mY + (local_service[1] - local_router[1] - imgWidth_base) / 2, Type.OPERATOR);
        }
    }

    private void setMyText(Canvas canvas, Double delay, int x, int y, Type type) {
        Paint textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeWidth(12);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
        switch (type) {
            case DEVICE:
            case LOCAL:
                if (delay == 0) {
                    textPaint.setColor(Color.parseColor("#F14466"));
                } else if (delay > 0 && delay <= 5) {
                    textPaint.setColor(Color.parseColor("#00B374"));
                } else if (delay > 5 && delay <= 10) {
                    textPaint.setColor(Color.parseColor("#EDCE2A"));
                } else if (delay > 10 && delay <= 20) {
                    textPaint.setColor(Color.parseColor("#F18E2D"));
                } else if (delay > 20) {
                    textPaint.setColor(Color.parseColor("#F14466"));
                }
                break;
            case OPERATOR:
                if (delay == 0) {
                    textPaint.setColor(Color.parseColor("#F14466"));
                } else if (delay > 0 && delay <= 10) {
                    textPaint.setColor(Color.parseColor("#00B374"));
                } else if (delay > 10 && delay <= 20) {
                    textPaint.setColor(Color.parseColor("#EDCE2A"));
                } else if (delay > 20 && delay <= 30) {
                    textPaint.setColor(Color.parseColor("#F18E2D"));
                } else if (delay > 30) {
                    textPaint.setColor(Color.parseColor("#F14466"));
                }
                break;
            case NODE:
                textPaint.setColor(Color.parseColor("#00B374"));
                break;
        }
        Paint bgRect = new Paint();
        bgRect.setStyle(Paint.Style.FILL);
        bgRect.setColor(Color.parseColor("#F9F9F9"));

        String ms = null;
        if (delay == 0) {
            ms = "丢包";
        } else {
            ms = String.format("%.1fms", delay);
        }
        float weight = textPaint.measureText(ms);
        canvas.drawRect(x - weight / 2, y - textSize, x + weight / 2, y + textSize, bgRect);
        canvas.drawText(ms, x, y + textSize / 2, textPaint);
    }

    public void setMyLineColor(Canvas canvas, Path path, Double delay, Type type) {
        switch (type) {
            case DEVICE:
            case LOCAL:
                if (delay == 0) {
                    canvas.drawPath(path, red_lost_paint);
                } else if (delay > 0 && delay <= 5) {
                    canvas.drawPath(path, green_paint);
                } else if (delay > 5 && delay <= 10) {
                    canvas.drawPath(path, yellow_paint);
                } else if (delay > 10 && delay <= 20) {
                    canvas.drawPath(path, orange_paint);
                } else if (delay > 20) {
                    canvas.drawPath(path, red_paint);
                }
                break;
            case OPERATOR:
                if (delay == 0) {
                    canvas.drawPath(path, red_lost_paint);
                } else if (delay > 0 && delay <= 10) {
                    canvas.drawPath(path, green_paint);
                } else if (delay > 10 && delay <= 20) {
                    canvas.drawPath(path, yellow_paint);
                } else if (delay > 20 && delay <= 30) {
                    canvas.drawPath(path, orange_paint);
                } else if (delay > 30) {
                    canvas.drawPath(path, red_paint);
                }
                break;
            case NODE:
                canvas.drawPath(path, green_paint);
                break;
        }
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
    public void addDevice(@DrawableRes int resId, final String name, final String ip, final Double delay) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        linearLayout.setGravity(Gravity.CENTER);
        final ImageView device = new ImageView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(imgWidth_base, imgHeight_base);
        device.setLayoutParams(params);
        device.setImageResource(resId);
        device.setBackgroundResource(R.drawable.network_background);
        device.setPadding(padding, padding, padding, padding);
        linearLayout.addView(device);
        devices_ll.addView(linearLayout);
        netDevices.add(new NetDevice(ip, name, delay, linearLayout, device));
        device.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final DevicePopup devicePopup = new DevicePopup(getContext());
                devicePopup.setText(name, "IP:" + ip);
                devicePopup.setBackgroundColor(Color.TRANSPARENT);
                devicePopup.setPopupGravity(Gravity.CENTER_HORIZONTAL);
                devicePopup.setOffsetY(dp2px(4, getContext()));
                devicePopup.showPopupWindow(v);
                devicePopups.add(devicePopup);
                devicePopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        devicePopups.remove(devicePopup);
                    }
                });
            }
        });
        invalidate();
    }

    /**
     * 添加节点
     *
     * @param name 设备名称
     */
    public void addNode(final String name, final Double delay) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(dp2px(30, getContext()), LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.setGravity(Gravity.CENTER);
        final TextView node = new TextView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(imgWidth_node, imgHeight_node);
        setMyTextBackgroundColor(node, delay);
        node.setLayoutParams(params);
        node.setText(name);
        node.setGravity(Gravity.CENTER);
        node.setTextColor(Color.parseColor("#3A3848"));
        linearLayout.addView(node);
        node_ll.addView(linearLayout);
        netNodes.add(new NetNode(name, delay, node, linearLayout));
        invalidate();
    }

    public void setMyTextBackgroundColor(TextView textview, Double delay) {
        textview.setBackground(getResources().getDrawable(R.drawable.network_green));
    }


    public int[] getLocation(View v) {
        int[] location = new int[2];
        v.getLocationInWindow(location);
        return location;
    }

    /**
     * 移除全部
     */
    public void removeAll() {
        netDevices.clear();
        netNodes.clear();
        invalidate();
    }

    /**
     * 移除全部设备
     */
    public void removeAllDevices() {
        netDevices.clear();
        devices_ll.removeAllViews();
        invalidate();
    }

    /**
     * 移除全部节点
     */
    public void removeAllNodes() {
        netNodes.clear();
        node_ll.removeAllViews();
        invalidate();
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
                netDevices.remove(netDevice);
            }
        }
        invalidate();
    }

    /**
     * 移除设备
     *
     * @param name 设备名称
     */
    public void removeNode(String name) {
        for (NetNode netNode : netNodes) {
            if (netNode.getName().equals(name)) {
                node_ll.removeView(netNode.getLinearLayout());
                netNodes.remove(netNode);
            }
        }
        invalidate();
    }

    /**
     * 刷新延迟
     *
     * @param name
     * @param delay
     */
    public void refreshDeviceAndNodeDelay(String name, Double delay) {
        dismissAllPopup();
        for (NetDevice netDevice : netDevices) {
            if (netDevice.getName().equals(name)) {
                netDevice.setDelay(delay);
            }
        }
        for (NetNode netNode : netNodes) {
            if (netNode.getName().equals(name)) {
                netNode.setDelay(delay);
                setMyTextBackgroundColor(netNode.getNode(), netNode.getDelay());
            }
        }
        invalidate();
    }

    /**
     * 刷新联机宝延迟
     *
     * @param delay
     */
    public void refreshLjbDelay(Double delay) {
        dismissAllPopup();
        ljbDelay = delay;
        invalidate();
    }

    /**
     * 刷新路由器延迟
     *
     * @param delay
     */
    public void refreshRouterDelay(Double delay) {
        dismissAllPopup();
        routerDelay = delay;
        invalidate();
    }

    /**
     * 获取设备
     *
     * @return
     */
    public CopyOnWriteArrayList<NetDevice> getNetDevices() {
        return netDevices;
    }

    /**
     * 获取设备
     *
     * @return
     */
    public CopyOnWriteArrayList<NetNode> getNetNode() {
        return netNodes;
    }

    /**
     * 关闭所有提示
     */
    public void dismissAllPopup() {
        for (DevicePopup devicePopup : devicePopups) {
            if (devicePopup.isShowing()) {
                devicePopup.dismiss();
                devicePopups.remove(devicePopup);
            }
        }
    }

    public boolean isOdd(int a) {
        if (a % 2 == 1) {   //是奇数
            return true;
        }
        return false;
    }
}
