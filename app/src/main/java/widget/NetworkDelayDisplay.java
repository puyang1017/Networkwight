package widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puy.networkwight.R;

import static android.view.Gravity.CENTER;

/**
 * 网络延时状态显示
 */
public class NetworkDelayDisplay extends RelativeLayout {
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

    private TextView text_ps4_to_ljb;
    private TextView text_xbox_to_ljb;
    private TextView text_center;
    private TextView text_ljb_to_router;
    private TextView text_router_to_node;
    private TextView text_1, text_name_1;
    private TextView text_2, text_name_2;
    private TextView text_3, text_name_3;
    private TextView text_4, text_name_4;
    private TextView text_5, text_name_5;
    private TextView text_6, text_name_6;
    private TextView text_7, text_name_7;
    private TextView text_8, text_name_8;
    private TextView text_9, text_name_9;
    private TextView text_10, text_name_10;
    private TextView text_only, text_name_only;

    private String name_1;
    private String name_2;
    private String name_3;
    private String name_4;
    private String name_5;
    private String name_6;
    private String name_7;
    private String name_8;
    private String name_9;
    private String name_10;
    private String name_buffer;

    private ImageView image_ps4;
    private ImageView image_xbox;
    private ImageView image_center;
    private ImageView image_ljb;
    private ImageView image_node;
    private ImageView image_router;
    private OnClickListener psOnClickListener;
    private OnClickListener xboxOnClickListener;
    private OnClickListener ljbOnClickListener;
    private OnClickListener routerOnClickListener;
    private OnClickListener nodeOnClickListener;

    private int imgHeight_base;
    private int imgHeight_node;
    private int imgWidth_node;
    private int padding;

    private int ps_to_ljb_delay;
    private int xbox_to_ljb_delay;
    private int ljb_to_router_delay;
    private int router_to_node_delay;
    private int delay_1;
    private int delay_2;
    private int delay_3;
    private int delay_4;
    private int delay_5;
    private int delay_6;
    private int delay_7;
    private int delay_8;
    private int delay_9;
    private int delay_10;
    private int delay_buffer;


    private boolean ps_to_ljb_lost = false;
    private boolean xbox_to_ljb_lost = false;
    private boolean ljb_to_router_lost = false;
    private boolean router_to_node_lost = false;
    private boolean lost_1 = false;
    private boolean lost_2 = false;
    private boolean lost_3 = false;
    private boolean lost_4 = false;
    private boolean lost_5 = false;
    private boolean lost_6 = false;
    private boolean lost_7 = false;
    private boolean lost_8 = false;
    private boolean lost_9 = false;
    private boolean lost_10 = false;
    private boolean lost_buffer = false;
    private int num;

    public NetworkDelayDisplay(Context context) {
        super(context);
    }

    public NetworkDelayDisplay(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
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

        //图片宽度
        imgHeight_base = dp2px(32, context);
        imgHeight_node = imgHeight_base;
        imgWidth_node = dp2px(60, context);
        //内边距
        padding = dp2px(3, context);


        //ps图标
        image_ps4 = new ImageView(getContext());
        image_ps4.setImageResource(R.drawable.icon_ps4_press);
        image_ps4.setBackgroundResource(R.drawable.network_background);
        image_ps4.setPadding(padding, padding, padding, padding);
        LayoutParams psParams = new LayoutParams(imgHeight_base, imgHeight_base);
        psParams.addRule(ALIGN_PARENT_LEFT);
        psParams.setMargins(0, 0, 0, 0);

        //xbox图标
        image_xbox = new ImageView(getContext());
        image_xbox.setImageResource(R.drawable.icon_xbox_focused);
        image_xbox.setBackgroundResource(R.drawable.network_background);
        image_xbox.setPadding(padding, padding, padding, padding);
        LayoutParams xboxParams = new LayoutParams(imgHeight_base, imgHeight_base);
        xboxParams.addRule(ALIGN_PARENT_RIGHT);
        xboxParams.setMargins(0, 0, 0, 0);

        //联机宝图标
        image_ljb = new ImageView(getContext());
        image_ljb.setImageResource(R.drawable.icon_lianjibao);
        image_ljb.setBackgroundResource(R.drawable.network_background);
        image_ljb.setPadding(padding, padding, padding, padding);
        LayoutParams ljbParams = new LayoutParams(imgHeight_base, imgHeight_base);
        ljbParams.addRule(CENTER_HORIZONTAL);
        ljbParams.setMargins(0, dp2px(72, getContext()), 0, 0);

        image_center = new ImageView(getContext());
        image_center.setBackgroundResource(R.drawable.network_background);
        image_center.setPadding(padding, padding, padding, padding);
        LayoutParams centerParams = new LayoutParams(imgHeight_base, imgHeight_base);
        centerParams.addRule(CENTER_HORIZONTAL);
        centerParams.setMargins(0, 0, 0, 0);

        //节点图标
        image_router = new ImageView(getContext());
        image_router.setImageResource(R.drawable.icon_router);
        image_router.setBackgroundResource(R.drawable.network_background);
        image_router.setPadding(padding, padding, padding, padding);
        LayoutParams routerParams = new LayoutParams(imgHeight_base, imgHeight_base);
        routerParams.addRule(CENTER_HORIZONTAL);
        routerParams.setMargins(0, dp2px(144, getContext()), 0, 0);

        //节点图标
        image_node = new ImageView(getContext());
        image_node.setImageResource(R.drawable.icon_node);
        image_node.setBackgroundResource(R.drawable.network_background);
        image_node.setPadding(padding, padding, padding, padding);
        LayoutParams nodeParams = new LayoutParams(imgWidth_node, imgHeight_node);
        nodeParams.addRule(CENTER_HORIZONTAL);
        nodeParams.setMargins(0, dp2px(216, getContext()), 0, 0);

        this.addView(image_ps4, psParams);
        this.addView(image_xbox, xboxParams);
        this.addView(image_ljb, ljbParams);
        this.addView(image_router, routerParams);
        this.addView(image_node, nodeParams);
        this.addView(image_center, centerParams);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (ps_to_ljb_delay > 0 && xbox_to_ljb_delay > 0) {
            Path ps_to_ljb_path = new Path();
            ps_to_ljb_path.moveTo(image_ps4.getX() + imgHeight_base / 2, image_ps4.getY() + imgHeight_base);
            ps_to_ljb_path.lineTo(image_ps4.getX() + imgHeight_base / 2, image_ps4.getY() + imgHeight_base + dp2px(20, getContext()));
            ps_to_ljb_path.lineTo(image_ljb.getX() + imgHeight_base / 3, image_ps4.getY() + imgHeight_base + dp2px(20, getContext()));
            ps_to_ljb_path.lineTo(image_ljb.getX() + imgHeight_base / 3, image_ljb.getY());
            setMyLineColor(canvas, ps_to_ljb_path, ps_to_ljb_delay, ps_to_ljb_lost);

            Path xbox_to_ljb_path = new Path();
            xbox_to_ljb_path.moveTo(image_xbox.getX() + imgHeight_base / 2, image_xbox.getY() + imgHeight_base);
            xbox_to_ljb_path.lineTo(image_xbox.getX() + imgHeight_base / 2, image_xbox.getY() + imgHeight_base + dp2px(20, getContext()));
            xbox_to_ljb_path.lineTo(image_ljb.getX() + imgHeight_base * 2 / 3, image_xbox.getY() + imgHeight_base + dp2px(20, getContext()));
            xbox_to_ljb_path.lineTo(image_ljb.getX() + imgHeight_base * 2 / 3, image_ljb.getY());
            setMyLineColor(canvas, xbox_to_ljb_path, xbox_to_ljb_delay, xbox_to_ljb_lost);
        } else {
            Path center_path = new Path();
            center_path.moveTo(image_center.getX() + imgHeight_base / 2, image_center.getY() + imgHeight_base);
            center_path.lineTo(image_ljb.getX() + imgHeight_base / 2, image_ljb.getY());

            if (ps_to_ljb_delay > 0) {
                setMyLineColor(canvas, center_path, ps_to_ljb_delay, ps_to_ljb_lost);
            }

            if (xbox_to_ljb_delay > 0) {
                setMyLineColor(canvas, center_path, xbox_to_ljb_delay, xbox_to_ljb_lost);
            }

        }


        if (ljb_to_router_delay > 0) {
            Path ljb_to_router_path = new Path();
            ljb_to_router_path.moveTo(image_ljb.getX() + imgHeight_base / 2, image_ljb.getY() + imgHeight_base);
            ljb_to_router_path.lineTo(image_router.getX() + imgHeight_base / 2, image_router.getY());
            setMyLineColor(canvas, ljb_to_router_path, ljb_to_router_delay, ljb_to_router_lost);
        }

        if (router_to_node_delay > 0) {
            Path router_to_node_path = new Path();
            router_to_node_path.moveTo(image_router.getX() + imgHeight_base / 2, image_router.getY() + imgHeight_base);
            router_to_node_path.lineTo(image_node.getX() + imgWidth_node / 2, image_node.getY());
            setMyLineColor(canvas, router_to_node_path, router_to_node_delay, router_to_node_lost);
        }

        if (num == 1) {
            if (delay_buffer > 0) {
                Path delay_only_path = new Path();
                delay_only_path.moveTo(image_node.getX() + imgWidth_node / 2, image_node.getY() + imgHeight_base);
                delay_only_path.lineTo(image_node.getX() + imgWidth_node / 2, image_node.getY() + imgHeight_base + dp2px(37, getContext()));
                delay_only_path.lineTo(width * 6 / 12, image_node.getY() + imgHeight_base + +dp2px(37, getContext()));
                delay_only_path.lineTo(width * 6 / 12, image_node.getY() + imgHeight_base + +dp2px(79, getContext()));
                setMyLineColor(canvas, delay_only_path, delay_1, lost_1);
            }
        } else {
            if (delay_1 > 0) {
                Path delay_1_path = new Path();
                delay_1_path.moveTo(image_node.getX() + imgWidth_node * 5 / 11, image_node.getY() + imgHeight_base);
                delay_1_path.lineTo(image_node.getX() + imgWidth_node * 5 / 11, image_node.getY() + imgHeight_base + dp2px(37, getContext()));
                delay_1_path.lineTo(width * 5 / 12, image_node.getY() + imgHeight_base + +dp2px(37, getContext()));
                delay_1_path.lineTo(width * 5 / 12, image_node.getY() + imgHeight_base + +dp2px(79, getContext()));
                setMyLineColor(canvas, delay_1_path, delay_1, lost_1);
            }

            if (delay_2 > 0) {
                Path delay_2_path = new Path();
                delay_2_path.moveTo(image_node.getX() + imgWidth_node * 6 / 11, image_node.getY() + imgHeight_base);
                delay_2_path.lineTo(image_node.getX() + imgWidth_node * 6 / 11, image_node.getY() + imgHeight_base + dp2px(37, getContext()));
                delay_2_path.lineTo(width * 7 / 12, image_node.getY() + imgHeight_base + +dp2px(37, getContext()));
                delay_2_path.lineTo(width * 7 / 12, image_node.getY() + imgHeight_base + +dp2px(79, getContext()));
                setMyLineColor(canvas, delay_2_path, delay_2, lost_2);
            }

            if (delay_3 > 0) {
                Path delay_3_path = new Path();
                delay_3_path.moveTo(image_node.getX() + imgWidth_node * 4 / 11, image_node.getY() + imgHeight_base);
                delay_3_path.lineTo(image_node.getX() + imgWidth_node * 4 / 11, image_node.getY() + imgHeight_base + dp2px(33, getContext()));
                delay_3_path.lineTo(width * 4 / 12, image_node.getY() + imgHeight_base + +dp2px(33, getContext()));
                delay_3_path.lineTo(width * 4 / 12, image_node.getY() + imgHeight_base + +dp2px(140, getContext()));
                setMyLineColor(canvas, delay_3_path, delay_3, lost_3);
            }

            if (delay_4 > 0) {
                Path delay_4_path = new Path();
                delay_4_path.moveTo(image_node.getX() + imgWidth_node * 7 / 11, image_node.getY() + imgHeight_base);
                delay_4_path.lineTo(image_node.getX() + imgWidth_node * 7 / 11, image_node.getY() + imgHeight_base + dp2px(33, getContext()));
                delay_4_path.lineTo(width * 8 / 12, image_node.getY() + imgHeight_base + +dp2px(33, getContext()));
                delay_4_path.lineTo(width * 8 / 12, image_node.getY() + imgHeight_base + +dp2px(140, getContext()));
                setMyLineColor(canvas, delay_4_path, delay_4, lost_4);
            }

            if (delay_5 > 0) {
                Path delay_5_path = new Path();
                delay_5_path.moveTo(image_node.getX() + imgWidth_node * 3 / 11, image_node.getY() + imgHeight_base);
                delay_5_path.lineTo(image_node.getX() + imgWidth_node * 3 / 11, image_node.getY() + imgHeight_base + dp2px(28, getContext()));
                delay_5_path.lineTo(width * 3 / 12, image_node.getY() + imgHeight_base + +dp2px(28, getContext()));
                delay_5_path.lineTo(width * 3 / 12, image_node.getY() + imgHeight_base + +dp2px(79, getContext()));
                setMyLineColor(canvas, delay_5_path, delay_5, lost_5);
            }

            if (delay_6 > 0) {
                Path delay_6_path = new Path();
                delay_6_path.moveTo(image_node.getX() + imgWidth_node * 8 / 11, image_node.getY() + imgHeight_base);
                delay_6_path.lineTo(image_node.getX() + imgWidth_node * 8 / 11, image_node.getY() + imgHeight_base + dp2px(28, getContext()));
                delay_6_path.lineTo(width * 9 / 12, image_node.getY() + imgHeight_base + +dp2px(28, getContext()));
                delay_6_path.lineTo(width * 9 / 12, image_node.getY() + imgHeight_base + +dp2px(79, getContext()));
                setMyLineColor(canvas, delay_6_path, delay_6, lost_6);
            }

            if (delay_7 > 0) {
                Path delay_7_path = new Path();
                delay_7_path.moveTo(image_node.getX() + imgWidth_node * 2 / 11, image_node.getY() + imgHeight_base);
                delay_7_path.lineTo(image_node.getX() + imgWidth_node * 2 / 11, image_node.getY() + imgHeight_base + dp2px(24, getContext()));
                delay_7_path.lineTo(width * 2 / 12, image_node.getY() + imgHeight_base + +dp2px(24, getContext()));
                delay_7_path.lineTo(width * 2 / 12, image_node.getY() + imgHeight_base + +dp2px(140, getContext()));
                setMyLineColor(canvas, delay_7_path, delay_7, lost_7);
            }

            if (delay_8 > 0) {
                Path delay_8_path = new Path();
                delay_8_path.moveTo(image_node.getX() + imgWidth_node * 9 / 11, image_node.getY() + imgHeight_base);
                delay_8_path.lineTo(image_node.getX() + imgWidth_node * 9 / 11, image_node.getY() + imgHeight_base + dp2px(24, getContext()));
                delay_8_path.lineTo(width * 10 / 12, image_node.getY() + imgHeight_base + +dp2px(24, getContext()));
                delay_8_path.lineTo(width * 10 / 12, image_node.getY() + imgHeight_base + +dp2px(140, getContext()));
                setMyLineColor(canvas, delay_8_path, delay_8, lost_8);
            }

            if (delay_9 > 0) {
                Path delay_9_path = new Path();
                delay_9_path.moveTo(image_node.getX() + imgWidth_node * 1 / 11, image_node.getY() + imgHeight_base);
                delay_9_path.lineTo(image_node.getX() + imgWidth_node * 1 / 11, image_node.getY() + imgHeight_base + dp2px(20, getContext()));
                delay_9_path.lineTo(width * 1 / 12, image_node.getY() + imgHeight_base + +dp2px(20, getContext()));
                delay_9_path.lineTo(width * 1 / 12, image_node.getY() + imgHeight_base + +dp2px(79, getContext()));
                setMyLineColor(canvas, delay_9_path, delay_9, lost_9);
            }

            if (delay_10 > 0) {
                Path delay_10_path = new Path();
                delay_10_path.moveTo(image_node.getX() + imgWidth_node * 10 / 11, image_node.getY() + imgHeight_base);
                delay_10_path.lineTo(image_node.getX() + imgWidth_node * 10 / 11, image_node.getY() + imgHeight_base + dp2px(20, getContext()));
                delay_10_path.lineTo(width * 11 / 12, image_node.getY() + imgHeight_base + +dp2px(20, getContext()));
                delay_10_path.lineTo(width * 11 / 12, image_node.getY() + imgHeight_base + +dp2px(79, getContext()));
                setMyLineColor(canvas, delay_10_path, delay_10, lost_10);
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }


    public int dp2px(float dp, Context mContext) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public void start() {
        this.post(new Runnable() {
            @Override
            public void run() {
                num = 0;//记录数量
                if (text_ps4_to_ljb != null) {
                    removeView(text_ps4_to_ljb);
                }
                if (text_xbox_to_ljb != null) {
                    removeView(text_xbox_to_ljb);
                }
                if (text_center != null) {
                    removeView(text_center);
                }
                if (text_ljb_to_router != null) {
                    removeView(text_ljb_to_router);
                }
                if (text_router_to_node != null) {
                    removeView(text_router_to_node);
                }
                if (text_1 != null) {
                    removeView(text_1);
                }
                if (text_name_1 != null) {
                    removeView(text_name_1);
                }
                if (text_2 != null) {
                    removeView(text_2);
                }
                if (text_name_2 != null) {
                    removeView(text_name_2);
                }
                if (text_3 != null) {
                    removeView(text_3);
                }
                if (text_name_3 != null) {
                    removeView(text_name_3);
                }
                if (text_4 != null) {
                    removeView(text_4);
                }
                if (text_name_4 != null) {
                    removeView(text_name_4);
                }
                if (text_5 != null) {
                    removeView(text_5);
                }
                if (text_name_5 != null) {
                    removeView(text_name_5);
                }
                if (text_6 != null) {
                    removeView(text_6);
                }
                if (text_name_6 != null) {
                    removeView(text_name_6);
                }
                if (text_7 != null) {
                    removeView(text_7);
                }
                if (text_name_7 != null) {
                    removeView(text_name_7);
                }
                if (text_8 != null) {
                    removeView(text_8);
                }
                if (text_name_8 != null) {
                    removeView(text_name_8);
                }
                if (text_9 != null) {
                    removeView(text_9);
                }
                if (text_name_9 != null) {
                    removeView(text_name_9);
                }
                if (text_10 != null) {
                    removeView(text_10);
                }
                if (text_name_10 != null) {
                    removeView(text_name_10);
                }
                if (text_only != null) {
                    removeView(text_only);
                }
                if (text_name_only != null) {
                    removeView(text_name_only);
                }

                if (delay_1 > 0) {
                    text_1 = new TextView(getContext());
                    text_1.setText(String.format("%dms", delay_1));
                    setMyTextColor(text_1, delay_1, lost_1);
                    text_1.setTextSize(dp2px(4, getContext()));
                    text_1.setBackgroundColor(text_background);
                    LayoutParams delay_1_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    delay_1_params.setMargins((int) (width * 5 / 12 - text_1.getPaint().measureText(String.format("%dms", delay_1)) / 2), (int) (image_node.getY() + imgHeight_base + dp2px(50, getContext())), 0, 0);
                    addView(text_1, delay_1_params);

                    text_name_1 = new TextView(getContext());
                    text_name_1.setText(name_1);
                    setMyTextBackgroundColor(text_name_1, delay_1, lost_1);
                    text_name_1.setGravity(CENTER);
                    text_name_1.setTextSize(dp2px(4, getContext()));
                    text_name_1.setTextColor(black);
                    LayoutParams name_1_params = new LayoutParams(dp2px(20, getContext()), dp2px(26, getContext()));
                    name_1_params.setMargins((int) (width * 5 / 12 - dp2px(10, getContext())), (int) (image_node.getY() + imgHeight_base + dp2px(79, getContext())), 0, 0);
                    addView(text_name_1, name_1_params);

                    num++;
                    delay_buffer = delay_1;
                    lost_buffer = lost_1;
                    name_buffer = name_1;
                    text_only = text_1;
                    text_name_only = text_name_1;
                }

                if (delay_2 > 0) {
                    text_2 = new TextView(getContext());
                    text_2.setText(String.format("%dms", delay_2));
                    setMyTextColor(text_2, delay_2, lost_2);
                    text_2.setTextSize(dp2px(4, getContext()));
                    text_2.setBackgroundColor(text_background);
                    LayoutParams delay_1_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    delay_1_params.setMargins((int) (width * 7 / 12 - text_2.getPaint().measureText(String.format("%dms", delay_2)) / 2), (int) (image_node.getY() + imgHeight_base + dp2px(50, getContext())), 0, 0);
                    addView(text_2, delay_1_params);

                    text_name_2 = new TextView(getContext());
                    text_name_2.setText(name_2);
                    setMyTextBackgroundColor(text_name_2, delay_2, lost_2);
                    text_name_2.setGravity(CENTER);
                    text_name_2.setTextSize(dp2px(4, getContext()));
                    text_name_2.setTextColor(black);
                    LayoutParams name_2_params = new LayoutParams(dp2px(20, getContext()), dp2px(26, getContext()));
                    name_2_params.setMargins((int) (width * 7 / 12 - dp2px(10, getContext())), (int) (image_node.getY() + imgHeight_base + dp2px(79, getContext())), 0, 0);
                    addView(text_name_2, name_2_params);
                    num++;
                    delay_buffer = delay_2;
                    lost_buffer = lost_2;
                    name_buffer = name_2;
                    text_only = text_2;
                    text_name_only = text_name_2;
                }

                if (delay_3 > 0) {
                    text_3 = new TextView(getContext());
                    text_3.setText(String.format("%dms", delay_3));
                    setMyTextColor(text_3, delay_3, lost_3);
                    text_3.setTextSize(dp2px(4, getContext()));
                    text_3.setBackgroundColor(text_background);
                    LayoutParams delay_1_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    delay_1_params.setMargins((int) (width * 4 / 12 - text_3.getPaint().measureText(String.format("%dms", delay_3)) / 2), (int) (image_node.getY() + imgHeight_base + dp2px(110, getContext())), 0, 0);
                    addView(text_3, delay_1_params);

                    text_name_3 = new TextView(getContext());
                    text_name_3.setText(name_3);
                    setMyTextBackgroundColor(text_name_3, delay_3, lost_3);
                    text_name_3.setGravity(CENTER);
                    text_name_3.setTextSize(dp2px(4, getContext()));
                    text_name_3.setTextColor(black);
                    LayoutParams name_3_params = new LayoutParams(dp2px(20, getContext()), dp2px(26, getContext()));
                    name_3_params.setMargins((int) (width * 4 / 12 - dp2px(10, getContext())), (int) (image_node.getY() + imgHeight_base + dp2px(140, getContext())), 0, 0);
                    addView(text_name_3, name_3_params);

                    num++;
                    delay_buffer = delay_3;
                    lost_buffer = lost_3;
                    name_buffer = name_3;
                    text_only = text_3;
                    text_name_only = text_name_3;
                }

                if (delay_4 > 0) {
                    text_4 = new TextView(getContext());
                    text_4.setText(String.format("%dms", delay_4));
                    setMyTextColor(text_4, delay_4, lost_4);
                    text_4.setTextSize(dp2px(4, getContext()));
                    text_4.setBackgroundColor(text_background);
                    LayoutParams delay_1_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    delay_1_params.setMargins((int) (width * 8 / 12 - text_4.getPaint().measureText(String.format("%dms", delay_4)) / 2), (int) (image_node.getY() + imgHeight_base + dp2px(110, getContext())), 0, 0);
                    addView(text_4, delay_1_params);

                    text_name_4 = new TextView(getContext());
                    text_name_4.setText(name_4);
                    setMyTextBackgroundColor(text_name_4, delay_4, lost_4);
                    text_name_4.setGravity(CENTER);
                    text_name_4.setTextSize(dp2px(4, getContext()));
                    text_name_4.setTextColor(black);
                    LayoutParams name_4_params = new LayoutParams(dp2px(20, getContext()), dp2px(26, getContext()));
                    name_4_params.setMargins((int) (width * 8 / 12 - dp2px(10, getContext())), (int) (image_node.getY() + imgHeight_base + dp2px(140, getContext())), 0, 0);
                    addView(text_name_4, name_4_params);

                    num++;
                    delay_buffer = delay_4;
                    lost_buffer = lost_4;
                    name_buffer = name_4;
                    text_only = text_4;
                    text_name_only = text_name_4;
                }

                if (delay_5 > 0) {
                    text_5 = new TextView(getContext());
                    text_5.setText(String.format("%dms", delay_5));
                    setMyTextColor(text_5, delay_5, lost_5);
                    text_5.setTextSize(dp2px(4, getContext()));
                    text_5.setBackgroundColor(text_background);
                    LayoutParams delay_5_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    delay_5_params.setMargins((int) (width * 3 / 12 - text_5.getPaint().measureText(String.format("%dms", delay_5)) / 2), (int) (image_node.getY() + imgHeight_base + dp2px(50, getContext())), 0, 0);
                    addView(text_5, delay_5_params);

                    text_name_5 = new TextView(getContext());
                    text_name_5.setText(name_5);
                    setMyTextBackgroundColor(text_name_5, delay_5, lost_5);
                    text_name_5.setGravity(CENTER);
                    text_name_5.setTextSize(dp2px(4, getContext()));
                    text_name_5.setTextColor(black);
                    LayoutParams name_5_params = new LayoutParams(dp2px(20, getContext()), dp2px(26, getContext()));
                    name_5_params.setMargins((int) (width * 3 / 12 - dp2px(10, getContext())), (int) (image_node.getY() + imgHeight_base + dp2px(79, getContext())), 0, 0);
                    addView(text_name_5, name_5_params);

                    num++;
                    delay_buffer = delay_5;
                    lost_buffer = lost_5;
                    name_buffer = name_5;
                    text_only = text_5;
                    text_name_only = text_name_5;
                }

                if (delay_6 > 0) {
                    text_6 = new TextView(getContext());
                    text_6.setText(String.format("%dms", delay_6));
                    setMyTextColor(text_6, delay_6, lost_6);
                    text_6.setTextSize(dp2px(4, getContext()));
                    text_6.setBackgroundColor(text_background);
                    LayoutParams delay_6_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    delay_6_params.setMargins((int) (width * 9 / 12 - text_6.getPaint().measureText(String.format("%dms", delay_6)) / 2), (int) (image_node.getY() + imgHeight_base + dp2px(50, getContext())), 0, 0);
                    addView(text_6, delay_6_params);

                    text_name_6 = new TextView(getContext());
                    text_name_6.setText(name_6);
                    setMyTextBackgroundColor(text_name_6, delay_6, lost_6);
                    text_name_6.setGravity(CENTER);
                    text_name_6.setTextSize(dp2px(4, getContext()));
                    text_name_6.setTextColor(black);
                    LayoutParams name_6_params = new LayoutParams(dp2px(20, getContext()), dp2px(26, getContext()));
                    name_6_params.setMargins((int) (width * 9 / 12 - dp2px(10, getContext())), (int) (image_node.getY() + imgHeight_base + dp2px(79, getContext())), 0, 0);
                    addView(text_name_6, name_6_params);

                    num++;
                    delay_buffer = delay_6;
                    lost_buffer = lost_6;
                    name_buffer = name_6;
                    text_only = text_6;
                    text_name_only = text_name_6;
                }

                if (delay_7 > 0) {
                    text_7 = new TextView(getContext());
                    text_7.setText(String.format("%dms", delay_7));
                    setMyTextColor(text_7, delay_7, lost_7);
                    text_7.setTextSize(dp2px(4, getContext()));
                    text_7.setBackgroundColor(text_background);
                    LayoutParams delay_7_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    delay_7_params.setMargins((int) (width * 2 / 12 - text_7.getPaint().measureText(String.format("%dms", delay_7)) / 2), (int) (image_node.getY() + imgHeight_base + dp2px(110, getContext())), 0, 0);
                    addView(text_7, delay_7_params);

                    text_name_7 = new TextView(getContext());
                    text_name_7.setText(name_7);
                    setMyTextBackgroundColor(text_name_7, delay_7, lost_7);
                    text_name_7.setGravity(CENTER);
                    text_name_7.setTextSize(dp2px(4, getContext()));
                    text_name_7.setTextColor(black);
                    LayoutParams name_7_params = new LayoutParams(dp2px(20, getContext()), dp2px(26, getContext()));
                    name_7_params.setMargins((int) (width * 2 / 12 - dp2px(10, getContext())), (int) (image_node.getY() + imgHeight_base + dp2px(140, getContext())), 0, 0);
                    addView(text_name_7, name_7_params);

                    num++;
                    delay_buffer = delay_7;
                    lost_buffer = lost_7;
                    name_buffer = name_7;
                    text_only = text_7;
                    text_name_only = text_name_7;
                }

                if (delay_8 > 0) {
                    text_8 = new TextView(getContext());
                    text_8.setText(String.format("%dms", delay_8));
                    setMyTextColor(text_8, delay_8, lost_8);
                    text_8.setTextSize(dp2px(4, getContext()));
                    text_8.setBackgroundColor(text_background);
                    LayoutParams delay_8_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    delay_8_params.setMargins((int) (width * 10 / 12 - text_8.getPaint().measureText(String.format("%dms", delay_8)) / 2), (int) (image_node.getY() + imgHeight_base + dp2px(110, getContext())), 0, 0);
                    addView(text_8, delay_8_params);

                    text_name_8 = new TextView(getContext());
                    text_name_8.setText(name_8);
                    setMyTextBackgroundColor(text_name_8, delay_8, lost_8);
                    text_name_8.setGravity(CENTER);
                    text_name_8.setTextSize(dp2px(4, getContext()));
                    text_name_8.setTextColor(black);
                    LayoutParams name_8_params = new LayoutParams(dp2px(20, getContext()), dp2px(26, getContext()));
                    name_8_params.setMargins((int) (width * 10 / 12 - dp2px(10, getContext())), (int) (image_node.getY() + imgHeight_base + dp2px(140, getContext())), 0, 0);
                    addView(text_name_8, name_8_params);

                    num++;
                    delay_buffer = delay_8;
                    lost_buffer = lost_8;
                    name_buffer = name_8;
                    text_only = text_8;
                    text_name_only = text_name_8;
                }

                if (delay_9 > 0) {
                    text_9 = new TextView(getContext());
                    text_9.setText(String.format("%dms", delay_9));
                    setMyTextColor(text_9, delay_9, lost_9);
                    text_9.setTextSize(dp2px(4, getContext()));
                    text_9.setBackgroundColor(text_background);
                    LayoutParams delay_9_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    delay_9_params.setMargins((int) (width * 1 / 12 - text_9.getPaint().measureText(String.format("%dms", delay_9)) / 2), (int) (image_node.getY() + imgHeight_base + dp2px(50, getContext())), 0, 0);
                    addView(text_9, delay_9_params);

                    text_name_9 = new TextView(getContext());
                    text_name_9.setText(name_9);
                    setMyTextBackgroundColor(text_name_9, delay_9, lost_9);
                    text_name_9.setGravity(CENTER);
                    text_name_9.setTextSize(dp2px(4, getContext()));
                    text_name_9.setTextColor(black);
                    LayoutParams name_9_params = new LayoutParams(dp2px(20, getContext()), dp2px(26, getContext()));
                    name_9_params.setMargins((int) (width * 1 / 12 - dp2px(10, getContext())), (int) (image_node.getY() + imgHeight_base + dp2px(79, getContext())), 0, 0);
                    addView(text_name_9, name_9_params);

                    num++;
                    delay_buffer = delay_9;
                    lost_buffer = lost_9;
                    name_buffer = name_9;
                    text_only = text_9;
                    text_name_only = text_name_9;
                }

                if (delay_10 > 0) {
                    text_10 = new TextView(getContext());
                    text_10.setText(String.format("%dms", delay_10));
                    setMyTextColor(text_10, delay_10, lost_10);
                    text_10.setTextSize(dp2px(4, getContext()));
                    text_10.setBackgroundColor(text_background);
                    LayoutParams delay_10_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    delay_10_params.setMargins((int) (width * 11 / 12 - text_10.getPaint().measureText(String.format("%dms", delay_10)) / 2), (int) (image_node.getY() + imgHeight_base + dp2px(50, getContext())), 0, 0);
                    addView(text_10, delay_10_params);

                    text_name_10 = new TextView(getContext());
                    text_name_10.setText(name_10);
                    setMyTextBackgroundColor(text_name_10, delay_10, lost_10);
                    text_name_10.setGravity(CENTER);
                    text_name_10.setTextSize(dp2px(4, getContext()));
                    text_name_10.setTextColor(black);
                    LayoutParams name_10_params = new LayoutParams(dp2px(20, getContext()), dp2px(26, getContext()));
                    name_10_params.setMargins((int) (width * 11 / 12 - dp2px(10, getContext())), (int) (image_node.getY() + imgHeight_base + dp2px(79, getContext())), 0, 0);
                    addView(text_name_10, name_10_params);

                    num++;
                    delay_buffer = delay_10;
                    lost_buffer = lost_10;
                    name_buffer = name_10;
                    text_only = text_10;
                    text_name_only = text_name_10;
                }

                if (num == 1) {//如果只有一个数据
                    if (delay_buffer > 0) {
                        if (text_only != null) {
                            removeView(text_only);
                        }
                        if (text_name_only != null) {
                            removeView(text_name_only);
                        }
                        removeView(text_only);
                        removeView(text_name_only);
                        text_only = new TextView(getContext());
                        text_only.setText(String.format("%dms", delay_buffer));
                        setMyTextColor(text_only, delay_buffer, lost_buffer);
                        text_only.setTextSize(dp2px(4, getContext()));
                        text_only.setBackgroundColor(text_background);
                        LayoutParams delay_only_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        delay_only_params.setMargins((int) (width * 6 / 12 - text_only.getPaint().measureText(String.format("%dms", delay_1)) / 2), (int) (image_node.getY() + imgHeight_base + dp2px(34, getContext())), 0, 0);
                        addView(text_only, delay_only_params);

                        text_name_only = new TextView(getContext());
                        text_name_only.setText(name_buffer);
                        setMyTextBackgroundColor(text_name_only, delay_buffer, lost_buffer);
                        text_name_only.setGravity(CENTER);
                        text_name_only.setTextSize(dp2px(4, getContext()));
                        text_name_only.setTextColor(black);
                        LayoutParams name_only_params = new LayoutParams(dp2px(20, getContext()), dp2px(26, getContext()));
                        name_only_params.setMargins((int) (width * 6 / 12 - dp2px(10, getContext())), (int) (image_node.getY() + imgHeight_base + dp2px(79, getContext())), 0, 0);
                        addView(text_name_only, name_only_params);
                    }
                }

                if (ps_to_ljb_delay > 0 && xbox_to_ljb_delay > 0) {//同时有ps和xbox
                    image_ps4.setVisibility(VISIBLE);
                    image_xbox.setVisibility(VISIBLE);
                    image_center.setVisibility(GONE);
                    text_ps4_to_ljb = new TextView(getContext());
                    text_ps4_to_ljb.setTextSize(dp2px(4, getContext()));
                    text_ps4_to_ljb.setText(String.format("%dms", ps_to_ljb_delay));
                    text_ps4_to_ljb.setBackgroundColor(text_background);
                    text_ps4_to_ljb.setPadding(dp2px(4, getContext()), 0, dp2px(4, getContext()), 0);
                    LayoutParams ps4_to_ljb_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    ps4_to_ljb_params.setMargins((int) (width / 3 - text_ps4_to_ljb.getPaint().measureText(String.format("%dms", ps_to_ljb_delay)) - dp2px(8, getContext())),
                            imgHeight_base + dp2px(10, getContext()), 0, 0);
                    setMyTextColor(text_ps4_to_ljb, ps_to_ljb_delay, ps_to_ljb_lost);
                    addView(text_ps4_to_ljb, ps4_to_ljb_params);

                    text_xbox_to_ljb = new TextView(getContext());
                    text_xbox_to_ljb.setTextSize(dp2px(4, getContext()));
                    text_xbox_to_ljb.setText(String.format("%dms", xbox_to_ljb_delay));
                    text_xbox_to_ljb.setBackgroundColor(text_background);
                    text_xbox_to_ljb.setPadding(dp2px(4, getContext()), 0, dp2px(4, getContext()), 0);
                    LayoutParams xbox_to_ljb_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    xbox_to_ljb_params.setMargins((int) (width * 2 / 3 + text_xbox_to_ljb.getPaint().measureText(String.format("%dms", xbox_to_ljb_delay)) / 2 - dp2px(8, getContext())),
                            imgHeight_base + dp2px(10, getContext()), 0, 0);
                    setMyTextColor(text_xbox_to_ljb, xbox_to_ljb_delay, xbox_to_ljb_lost);
                    addView(text_xbox_to_ljb, xbox_to_ljb_params);
                } else {
                    image_ps4.setVisibility(GONE);
                    image_xbox.setVisibility(GONE);
                    image_center.setVisibility(VISIBLE);
                    text_center = new TextView(getContext());
                    if (ps_to_ljb_delay > 0) {//只有ps
                        image_center.setImageResource(R.drawable.icon_ps4_press);
                        text_center.setText(String.format("%dms", ps_to_ljb_delay));
                        setMyTextColor(text_center, ps_to_ljb_delay, ps_to_ljb_lost);
                        text_center.setTextSize(dp2px(4, getContext()));
                        text_center.setBackgroundColor(text_background);
                        text_center.setPadding(dp2px(4, getContext()), 0, dp2px(4, getContext()), 0);
                        LayoutParams center_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        center_params.setMargins((int) (width / 2 - text_center.getPaint().measureText(String.format("%dms", ps_to_ljb_delay)) / 2), imgHeight_base + dp2px(10, getContext()), 0, 0);
                        addView(text_center, center_params);
                        if (psOnClickListener != null) {
                            image_center.setOnClickListener(psOnClickListener);
                        }
                    } else if (xbox_to_ljb_delay > 0) {//只有xbox
                        image_center.setImageResource(R.drawable.icon_xbox_focused);
                        text_center.setText(String.format("%dms", xbox_to_ljb_delay));
                        setMyTextColor(text_center, xbox_to_ljb_delay, xbox_to_ljb_lost);
                        text_center.setTextSize(dp2px(4, getContext()));
                        text_center.setBackgroundColor(text_background);
                        text_center.setPadding(dp2px(4, getContext()), 0, dp2px(4, getContext()), 0);
                        LayoutParams center_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        center_params.setMargins((int) (width / 2 - text_center.getPaint().measureText(String.format("%dms", xbox_to_ljb_delay)) / 2), imgHeight_base + dp2px(10, getContext()), 0, 0);
                        addView(text_center, center_params);
                        if (xboxOnClickListener != null) {
                            image_center.setOnClickListener(xboxOnClickListener);
                        }
                    }
                }

                if (ljb_to_router_delay > 0) {
                    text_ljb_to_router = new TextView(getContext());
                    text_ljb_to_router.setText(String.format("%dms", ljb_to_router_delay));
                    setMyTextColor(text_ljb_to_router, ljb_to_router_delay, ljb_to_router_lost);
                    text_ljb_to_router.setTextSize(dp2px(4, getContext()));
                    text_ljb_to_router.setBackgroundColor(text_background);
                    LayoutParams center_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    center_params.setMargins((int) (width / 2 - text_ljb_to_router.getPaint().measureText(String.format("%dms", ljb_to_router_delay)) / 2), (int) (image_ljb.getY() + imgHeight_base + dp2px(10, getContext())), 0, 0);
                    addView(text_ljb_to_router, center_params);
                }

                if (router_to_node_delay > 0) {
                    text_router_to_node = new TextView(getContext());
                    text_router_to_node.setText(String.format("%dms", router_to_node_delay));
                    setMyTextColor(text_router_to_node, router_to_node_delay, router_to_node_lost);
                    text_router_to_node.setTextSize(dp2px(4, getContext()));
                    text_router_to_node.setBackgroundColor(text_background);
                    LayoutParams center_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    center_params.setMargins((int) (width / 2 - text_router_to_node.getPaint().measureText(String.format("%dms", router_to_node_delay)) / 2), (int) (image_router.getY() + imgHeight_base + dp2px(10, getContext())), 0, 0);
                    addView(text_router_to_node, center_params);
                }
                if (psOnClickListener != null) {
                    image_ps4.setOnClickListener(psOnClickListener);
                }
                if (xboxOnClickListener != null) {
                    image_xbox.setOnClickListener(xboxOnClickListener);
                }
                if (ljbOnClickListener != null) {
                    image_ljb.setOnClickListener(ljbOnClickListener);
                }
                if (routerOnClickListener != null) {
                    image_router.setOnClickListener(routerOnClickListener);
                }
                if (nodeOnClickListener != null) {
                    image_node.setOnClickListener(nodeOnClickListener);
                }

            }
        });
    }

    //设置延时文字颜色
    public void setMyTextColor(TextView textview, int delay, boolean lost) {
        if (lost) {
            textview.setTextColor(red);
        } else {
            if (delay > 0 && delay < 100) {
                textview.setTextColor(green);
            } else if (delay >= 100 && delay < 150) {
                textview.setTextColor(blue);
            } else if (delay >= 150 && delay < 200) {
                textview.setTextColor(yellow);
            } else if (delay >= 200 && delay < 300) {
                textview.setTextColor(orange);
            } else if (delay >= 300) {
                textview.setTextColor(red);
            }
        }
    }

    public void setMyTextBackgroundColor(TextView textview, int delay, boolean lost) {
        if (lost) {
            textview.setBackground(getResources().getDrawable(R.drawable.network_red));
        } else {
            if (delay > 0 && delay < 100) {
                textview.setBackground(getResources().getDrawable(R.drawable.network_green));
            } else if (delay >= 100 && delay < 150) {
                textview.setBackground(getResources().getDrawable(R.drawable.network_blue));
            } else if (delay >= 150 && delay < 200) {
                textview.setBackground(getResources().getDrawable(R.drawable.network_yellow));
            } else if (delay >= 200 && delay < 300) {
                textview.setBackground(getResources().getDrawable(R.drawable.network_orange));
            } else if (delay >= 300) {
                textview.setBackground(getResources().getDrawable(R.drawable.network_red));
            }
        }
    }

    public void setMyLineColor(Canvas canvas, Path path, int delay, boolean lost) {
        if (lost) {
            canvas.drawPath(path, red_lost_paint);
        } else {
            if (delay > 0 && delay < 100) {
                canvas.drawPath(path, green_paint);
            } else if (delay >= 100 && delay < 150) {
                canvas.drawPath(path, blue_paint);
            } else if (delay >= 150 && delay < 200) {
                canvas.drawPath(path, yellow_paint);
            } else if (delay >= 200 && delay < 300) {
                canvas.drawPath(path, orange_paint);
            } else if (delay >= 300) {
                canvas.drawPath(path, red_paint);
            }
        }
    }

    public NetworkDelayDisplay setPs_to_ljb_delay(int ps_to_ljb_delay, boolean ps_to_ljb_lost) {
        this.ps_to_ljb_delay = ps_to_ljb_delay;
        this.ps_to_ljb_lost = ps_to_ljb_lost;
        return this;
    }

    public NetworkDelayDisplay setXbox_to_ljb_delay(int xbox_to_ljb_delay, boolean xbox_to_ljb_lost) {
        this.xbox_to_ljb_delay = xbox_to_ljb_delay;
        this.xbox_to_ljb_lost = xbox_to_ljb_lost;
        return this;
    }

    public NetworkDelayDisplay setLjb_to_router_delay(int ljb_to_router_delay, boolean ljb_to_router_lost) {
        this.ljb_to_router_delay = ljb_to_router_delay;
        this.ljb_to_router_lost = ljb_to_router_lost;
        return this;
    }

    public NetworkDelayDisplay setRouter_to_node_delay(int router_to_node_delay, boolean router_to_node_lost) {
        this.router_to_node_delay = router_to_node_delay;
        this.router_to_node_lost = router_to_node_lost;
        return this;
    }

    //重置数据
    public NetworkDelayDisplay resetData() {
        name_1 = null;
        name_2 = null;
        name_3 = null;
        name_4 = null;
        name_5 = null;
        name_6 = null;
        name_7 = null;
        name_8 = null;
        name_9 = null;
        name_10 = null;
        name_buffer = null;


        psOnClickListener = null;
        xboxOnClickListener = null;
        ljbOnClickListener = null;
        routerOnClickListener = null;
        nodeOnClickListener = null;

        ps_to_ljb_delay = 0;
        xbox_to_ljb_delay = 0;
        ljb_to_router_delay = 0;
        router_to_node_delay = 0;
        delay_1 = 0;
        delay_2 = 0;
        delay_3 = 0;
        delay_4 = 0;
        delay_5 = 0;
        delay_6 = 0;
        delay_7 = 0;
        delay_8 = 0;
        delay_9 = 0;
        delay_10 = 0;
        delay_buffer = 0;


        ps_to_ljb_lost = false;
        xbox_to_ljb_lost = false;
        ljb_to_router_lost = false;
        router_to_node_lost = false;
        lost_1 = false;
        lost_2 = false;
        lost_3 = false;
        lost_4 = false;
        lost_5 = false;
        lost_6 = false;
        lost_7 = false;
        lost_8 = false;
        lost_9 = false;
        lost_10 = false;
        lost_buffer = false;
        num = 0;
        return this;
    }

    public NetworkDelayDisplay add_node_to_country(int delay, boolean lost, String name) {
        num++;
        switch (num) {
            case 1:
                this.delay_1 = delay;
                this.lost_1 = lost;
                this.name_1 = name;
                break;
            case 2:
                this.delay_2 = delay;
                this.lost_2 = lost;
                this.name_2 = name;
                break;
            case 3:
                this.delay_3 = delay;
                this.lost_3 = lost;
                this.name_3 = name;
                break;
            case 4:
                this.delay_4 = delay;
                this.lost_4 = lost;
                this.name_4 = name;
                break;
            case 5:
                this.delay_5 = delay;
                this.lost_5 = lost;
                this.name_5 = name;
                break;
            case 6:
                this.delay_6 = delay;
                this.lost_6 = lost;
                this.name_6 = name;
                break;
            case 7:
                this.delay_7 = delay;
                this.lost_7 = lost;
                this.name_7 = name;
                break;
            case 8:
                this.delay_8 = delay;
                this.lost_8 = lost;
                this.name_8 = name;
                break;
            case 9:
                this.delay_9 = delay;
                this.lost_9 = lost;
                this.name_9 = name;
                break;
            case 10:
                this.delay_10 = delay;
                this.lost_10 = lost;
                this.name_10 = name;
                break;
        }

        return this;
    }

    public NetworkDelayDisplay set_node_to_country_1(int delay_1, boolean lost_1, String name_1) {
        this.delay_1 = delay_1;
        this.lost_1 = lost_1;
        this.name_1 = name_1;
        return this;
    }

    public NetworkDelayDisplay set_node_to_country_2(int delay_2, boolean lost_2, String name_2) {
        this.delay_2 = delay_2;
        this.lost_2 = lost_2;
        this.name_2 = name_2;
        return this;
    }

    public NetworkDelayDisplay set_node_to_country_3(int delay_3, boolean lost_3, String name_3) {
        this.delay_3 = delay_3;
        this.lost_3 = lost_3;
        this.name_3 = name_3;
        return this;
    }

    public NetworkDelayDisplay set_node_to_country_4(int delay_4, boolean lost_4, String name_4) {
        this.delay_4 = delay_4;
        this.lost_4 = lost_4;
        this.name_4 = name_4;
        return this;
    }

    public NetworkDelayDisplay set_node_to_country_5(int delay_5, boolean lost_5, String name_5) {
        this.delay_5 = delay_5;
        this.lost_5 = lost_5;
        this.name_5 = name_5;
        return this;
    }

    public NetworkDelayDisplay set_node_to_country_6(int delay_6, boolean lost_6, String name_6) {
        this.delay_6 = delay_6;
        this.lost_6 = lost_6;
        this.name_6 = name_6;
        return this;
    }

    public NetworkDelayDisplay set_node_to_country_7(int delay_7, boolean lost_7, String name_7) {
        this.delay_7 = delay_7;
        this.lost_7 = lost_7;
        this.name_7 = name_7;
        return this;
    }

    public NetworkDelayDisplay set_node_to_country_8(int delay_8, boolean lost_8, String name_8) {
        this.delay_8 = delay_8;
        this.lost_8 = lost_8;
        this.name_8 = name_8;
        return this;
    }

    public NetworkDelayDisplay set_node_to_country_9(int delay_9, boolean lost_9, String name_9) {
        this.delay_9 = delay_9;
        this.lost_9 = lost_9;
        this.name_9 = name_9;
        return this;
    }

    public NetworkDelayDisplay set_node_to_country_10(int delay_10, boolean lost_10, String name_10) {
        this.delay_10 = delay_10;
        this.lost_10 = lost_10;
        this.name_10 = name_10;
        return this;
    }

    public NetworkDelayDisplay setPsOnClickListener(OnClickListener psOnClickLister) {
        this.psOnClickListener = psOnClickLister;
        return this;
    }

    public NetworkDelayDisplay setXboxOnClickListener(OnClickListener xboxOnClickLister) {
        this.xboxOnClickListener = xboxOnClickLister;
        return this;
    }

    public NetworkDelayDisplay setLjbOnClickListener(OnClickListener ljbOnClickLister) {
        this.ljbOnClickListener = ljbOnClickLister;
        return this;
    }

    public NetworkDelayDisplay setRouterOnClickListener(OnClickListener routerOnClickListener) {
        this.routerOnClickListener = routerOnClickListener;
        return this;
    }

    public NetworkDelayDisplay setNodeOnClickListener(OnClickListener nodeOnClickLister) {
        this.nodeOnClickListener = nodeOnClickLister;
        return this;
    }
}
