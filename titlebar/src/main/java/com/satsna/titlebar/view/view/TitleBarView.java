package com.satsna.titlebar.view.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.satsna.titlebar.R;


public class TitleBarView extends RelativeLayout {


    protected ImageView icon_left;
    protected TextView tv_title, tv_right;
    protected ImageView icon_right;
    protected RelativeLayout rl_titlebar, rl_left, rl_center, rl_right;

    /**
     * 获取标题
     *
     * @return
     */
    public String getTtitle() {

        return tv_title.getText().toString();
    }

    /**
     * 构造方法;
     */
    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflate(context, R.layout.layout_titlebar, this);
        if (isInEditMode()) {
            return;
        }

        init();
        setAttribute(context, attrs);
    }

    /**
     * 初始化
     */
    private void init() {
        icon_left = findViewById(R.id.icon_left);
        tv_title = findViewById(R.id.tv_title);
        icon_right = findViewById(R.id.icon_right);
        tv_right = findViewById(R.id.tv_right);

        rl_titlebar = findViewById(R.id.rl_titlebar);//整个布局
        rl_left = findViewById(R.id.rl_left);
        rl_center = findViewById(R.id.rl_center);
        rl_right = findViewById(R.id.rl_right);
    }

    /**
     * 获取布局属性参数并设置到控件上
     *
     * @param context
     * @param attrs
     */
    public void setAttribute(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBarView);

        //左边图片
        Integer leftVisibility = typedArray.getInt(R.styleable.TitleBarView_leftVisibility, -1);
        switch (leftVisibility) {
            //未取到值
            case -1:
                icon_left.setVisibility(INVISIBLE);
                break;
            //隐藏invisible
            case 1:
                icon_left.setVisibility(INVISIBLE);
                break;
            //可见visibleImage
            case 2:
                icon_left.setVisibility(VISIBLE);
                int resourceId = typedArray.getResourceId(R.styleable.TitleBarView_leftSrc, -1);
                if (resourceId != -1) {
                    icon_left.setImageResource(resourceId);
                }
                break;
            default:
                icon_left.setVisibility(INVISIBLE);
                break;
        }

        //中间文字
        Integer centerVisibility = typedArray.getInt(R.styleable.TitleBarView_centerVisibility, -1);
        switch (centerVisibility) {
            //未取到值
            case -1:
                tv_title.setVisibility(INVISIBLE);
                break;
            //隐藏invisible
            case 1:
                tv_title.setVisibility(INVISIBLE);
                break;
            //可见visibleTextView
            case 2:
                tv_title.setVisibility(VISIBLE);
                String titleStr = typedArray.getString(R.styleable.TitleBarView_centerText);
                tv_title.setText(titleStr == null ? "" : titleStr);

                int color = typedArray.getColor(R.styleable.TitleBarView_centerTextColor, -1);
                if (color != -1) {
                    tv_title.setTextColor(color);
                }
                float size = typedArray.getDimensionPixelSize(R.styleable.TitleBarView_centerTextSize, -1);
                if (size != -1) {
                    tv_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
                }
                break;
            default:
                tv_title.setVisibility(INVISIBLE);
                break;
        }


        //右边
        Integer rightVisibility = typedArray.getInt(R.styleable.TitleBarView_rightVisibility, -1);
        switch (rightVisibility) {
            //未取到值
            case -1:
                tv_right.setVisibility(INVISIBLE);
                icon_right.setVisibility(INVISIBLE);
                break;
            //隐藏invisible
            case 1:
                tv_right.setVisibility(INVISIBLE);
                icon_right.setVisibility(INVISIBLE);
                break;
            //可见visibleImage
            case 2:
                tv_right.setVisibility(INVISIBLE);
                icon_right.setVisibility(VISIBLE);
                int resourceId = typedArray.getResourceId(R.styleable.TitleBarView_rightSrc, -1);
                if (resourceId != -1) {
                    icon_right.setImageResource(resourceId);
                }
                break;
            //可见visibleTextView
            case 3:
                tv_right.setVisibility(VISIBLE);
                icon_right.setVisibility(INVISIBLE);
                String rightStr = typedArray.getString(R.styleable.TitleBarView_rightText);
                tv_right.setText(rightStr == null ? "" : rightStr);
                int color = typedArray.getColor(R.styleable.TitleBarView_rightTextColor, -1);
                if (color != -1) {
                    tv_right.setTextColor(color);
                }
                float size = typedArray.getDimension(R.styleable.TitleBarView_rightTextSize, -1);
                if (size != -1) {
                    tv_right.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
                }
                break;
            default:
                tv_right.setVisibility(INVISIBLE);
                icon_right.setVisibility(INVISIBLE);
                break;
        }
        typedArray.recycle();
    }


    /**
     * 初始化标题栏:控件不能为空且没有点击事件;
     *
     * @param icon_leftID  左边图片ID
     * @param title        标题
     * @param icon_rightID 右边图片ID
     */
    public void initTitleBar(int icon_leftID, String title, int icon_rightID) {
        if (icon_leftID > 0) {
            icon_left.setImageResource(icon_leftID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (icon_rightID > 0) {
            icon_right.setImageResource(icon_rightID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (null == title || title.length() == 0) {
            throw new RuntimeException("标题不能为空");
        } else {
            tv_title.setText(title);
        }
    }

    /**
     * 初始化标题栏:左边图片和标题的ID
     *
     * @param icon_leftID 左边图片ID
     * @param titleID     标题ID
     */
    public void initLeftTitleBar(int icon_leftID, int titleID) {

        rl_right.setVisibility(View.INVISIBLE);//把右边的控件设置隐藏
        if (icon_leftID > 0) {
            icon_left.setImageResource(icon_leftID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (titleID > 0) {
            tv_title.setText(getResources().getString(titleID));
        } else {
            throw new RuntimeException("标题不能为空");
        }
    }

    /**
     * 初始化标题栏==>>设置标题
     *
     * @param icon_leftID 左边图片ID
     * @param title       标题
     */
    public void initLeftTitleBar(int icon_leftID, String title) {
        rl_right.setVisibility(View.INVISIBLE);//把右边的控件设置隐藏
        if (icon_leftID > 0) {
            icon_left.setImageResource(icon_leftID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (null == title || title.length() == 0) {
            throw new RuntimeException("标题不能为空");
        } else {
            tv_title.setText(title);
        }
    }


    /**
     * 初始化标题栏
     *
     * @param icon_leftID  左边图片ID
     * @param titleID      标题ID
     * @param leftListener 左边控件监听事件:监听事件在Activity或者Fragment中写;
     */
    public void initLeftTitleBar(int icon_leftID, int titleID, OnClickListener leftListener) {

        rl_right.setVisibility(View.INVISIBLE);//把右边的控件设置隐藏

        if (icon_leftID > 0) {
            icon_left.setImageResource(icon_leftID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (titleID > 0) {
            tv_title.setText(getResources().getString(titleID));
        } else {
            throw new RuntimeException("标题不能为空");
        }

        if (null == leftListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_left.setOnClickListener(leftListener);
        }
    }

    /**
     * 初始化标题栏
     *
     * @param icon_leftID  左边图片ID
     * @param title        标题ID
     * @param leftListener 左边控件监听事件
     */
    public void initLeftTitleBar(int icon_leftID, String title, OnClickListener leftListener
    ) {
        rl_right.setVisibility(View.INVISIBLE);
        if (icon_leftID > 0) {
            icon_left.setImageResource(icon_leftID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (TextUtils.isEmpty(title)) {
            throw new RuntimeException("标题不能为空");
        } else {
            tv_title.setText(title);
        }

        if (null == leftListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_left.setOnClickListener(leftListener);
        }
    }

    /**
     * 初始化标题栏
     *
     * @param icon_leftID  左边图片ID
     * @param leftListener 左边控件监听事件
     */
    public void initLeftTitleBar(int icon_leftID, OnClickListener leftListener
    ) {
        rl_right.setVisibility(View.INVISIBLE);
        if (icon_leftID > 0) {
            icon_left.setImageResource(icon_leftID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (null == leftListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_left.setOnClickListener(leftListener);
        }
    }


    /**
     * 初始化标题栏
     *
     * @param titleID      标题ID
     * @param icon_rightID 右边图片ID
     */
    public void initRightTitleBar(int titleID, int icon_rightID
    ) {
        rl_left.setVisibility(View.INVISIBLE);
        if (icon_rightID > 0) {
            icon_right.setImageResource(icon_rightID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (titleID > 0) {
            tv_title.setText(getResources().getString(titleID));
        } else {
            throw new RuntimeException("标题不能为空");
        }
    }

    /**
     * 初始化标题栏
     *
     * @param title        标题
     * @param icon_rightID 右边图片ID
     */
    public void initRightTitleBar(String title, int icon_rightID
    ) {
        rl_left.setVisibility(View.INVISIBLE);
        if (icon_rightID > 0) {
            icon_right.setImageResource(icon_rightID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (null == title || title.length() == 0) {
            throw new RuntimeException("标题不能为空");
        } else {
            tv_title.setText(title);
        }
    }


    /**
     * 初始化标题栏
     *
     * @param titleID       标题ID
     * @param icon_rightID  右边图片ID
     * @param rightListener 右边控件监听事件
     */
    public void initRightTitleBar(int titleID, int icon_rightID, OnClickListener rightListener
    ) {
        rl_left.setVisibility(View.INVISIBLE);
        if (icon_rightID > 0) {
            icon_right.setImageResource(icon_rightID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (titleID > 0) {
            tv_title.setText(getResources().getString(titleID));
        } else {
            throw new RuntimeException("标题不能为空");
        }

        if (null == rightListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_right.setOnClickListener(rightListener);
        }
    }

    /**
     * 初始化标题栏
     *
     * @param titleID       标题ID
     * @param str_right     右边图片ID
     * @param rightListener 右边控件监听事件
     */
    public void initRightTitleBar(int titleID, String str_right, OnClickListener rightListener
    ) {
        rl_left.setVisibility(View.INVISIBLE);
        if (titleID > 0) {
            tv_title.setText(getResources().getString(titleID));
        } else {
            throw new RuntimeException("标题不能为空");
        }
        if (!TextUtils.isEmpty(str_right)) {
            tv_right.setVisibility(VISIBLE);
            tv_right.setText(str_right);
        } else {
            throw new RuntimeException("右边标题不能为空");
        }

        if (null == rightListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_right.setOnClickListener(rightListener);
        }
    }

    /**
     * 初始化标题栏
     *
     * @param icon_leftID   左边图片ID
     * @param titleID       标题ID
     * @param icon_rightID  右边图片ID
     * @param leftListener  左边控件监听事件
     * @param rightListener 右边控件监听事件
     */
    public void initTitleBar(int icon_leftID, int titleID, int icon_rightID, OnClickListener leftListener, OnClickListener rightListener
    ) {
        if (icon_leftID > 0) {
            icon_left.setImageResource(icon_leftID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (icon_rightID > 0) {
            icon_right.setImageResource(icon_rightID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (titleID > 0) {
            tv_title.setText(getResources().getString(titleID));
        } else {
            throw new RuntimeException("标题不能为空");
        }

        if (null == leftListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_left.setOnClickListener(leftListener);
        }

        if (null == rightListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_right.setOnClickListener(rightListener);
        }
    }

    /**
     * 初始化标题栏
     *
     * @param icon_leftID   左边图片ID
     * @param str_right     标题
     * @param str_right     右边标题
     * @param leftListener  左边控件监听事件
     * @param rightListener 右边控件监听事件
     */
    public void initTitleBar(int icon_leftID, String title, String str_right, OnClickListener leftListener, OnClickListener rightListener
    ) {
        if (icon_leftID > 0) {
            icon_left.setImageResource(icon_leftID);
        } else {
//            throw new RuntimeException("控件不能为空");
            icon_left.setVisibility(INVISIBLE);
        }
        if (!TextUtils.isEmpty(str_right)) {
            tv_right.setVisibility(View.VISIBLE);
            tv_right.setText(str_right);
        } else {
//            throw new RuntimeException("右边标题不能为空");
            tv_right.setVisibility(INVISIBLE);
        }
        if (!TextUtils.isEmpty(str_right)) {
            tv_title.setText(title);
        } else {
//            throw new RuntimeException("标题不能为空");
            tv_title.setVisibility(INVISIBLE);
        }

        if (null == leftListener) {
//            throw new RuntimeException("监听不能为空");
        } else {
            rl_left.setOnClickListener(leftListener);
        }

        if (null == rightListener) {
//            throw new RuntimeException("监听不能为空");
        } else {
            rl_right.setOnClickListener(rightListener);
        }
    }

    /**
     * 初始化标题栏
     *
     * @param icon_leftID    左边图片ID
     * @param titleID        标题ID
     * @param icon_rightID   右边图片ID
     * @param leftListener   左边控件监听事件
     * @param centerListener 中间监听事件
     * @param rightListener  右边控件监听事件
     */
    public void initTitleBar(int icon_leftID, int titleID, int icon_rightID, OnClickListener leftListener, OnClickListener centerListener, OnClickListener rightListener
    ) {
        if (icon_leftID > 0) {
            icon_left.setImageResource(icon_leftID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (icon_rightID > 0) {
            icon_right.setImageResource(icon_rightID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (titleID > 0) {
            tv_title.setText(getResources().getString(titleID));
        } else {
            throw new RuntimeException("标题不能为空");
        }

        if (null == leftListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_left.setOnClickListener(leftListener);
        }
        if (null == centerListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_center.setOnClickListener(centerListener);
        }

        if (null == rightListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_right.setOnClickListener(rightListener);
        }
    }

    /**
     * 初始化标题栏
     *
     * @param icon_leftID    左边图片ID
     * @param titleID        标题ID
     * @param icon_rightID   右边图片ID
     * @param str_rightID    左边文字
     * @param leftListener   左边控件监听事件
     * @param centerListener 中间监听事件
     * @param rightListener  右边控件监听事件
     */
    public void initTitleBar(int icon_leftID, int titleID, int icon_rightID, int str_rightID, OnClickListener leftListener, OnClickListener centerListener, OnClickListener rightListener
    ) {
        if (icon_leftID > 0) {
            icon_left.setImageResource(icon_leftID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (icon_rightID > 0) {
            icon_right.setImageResource(icon_rightID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (str_rightID > 0) {
            tv_right.setText(getResources().getString(str_rightID));
        } else {
            throw new RuntimeException("右边文字不能为空");
        }
        if (titleID > 0) {
            tv_title.setText(getResources().getString(titleID));
        } else {
            throw new RuntimeException("标题不能为空");
        }

        if (null == leftListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_left.setOnClickListener(leftListener);
        }
        if (null == centerListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_center.setOnClickListener(centerListener);
        }

        if (null == rightListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_right.setOnClickListener(rightListener);
        }
    }

    /**
     * 初始化标题栏
     *
     * @param titleID       标题ID
     * @param str_rightID   右边边文字
     * @param icon_rightID  右边图片ID
     * @param rightListener 右边控件监听事件
     */
    public void initRigthtTitleBar(int titleID, int str_rightID, int icon_rightID, OnClickListener rightListener) {
        rl_left.setVisibility(View.INVISIBLE);
        rl_right.setVisibility(VISIBLE);
        tv_right.setVisibility(VISIBLE);
        if (null == rightListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_right.setOnClickListener(rightListener);
        }

        if (titleID > 0) {
            tv_title.setText(getResources().getString(titleID));
        } else {
            throw new RuntimeException("标题不能为空");
        }

        if (str_rightID > 0) {
            tv_right.setText(getResources().getString(str_rightID));
        } else {
            throw new RuntimeException("右边文字不能为空");
        }

        if (icon_rightID > 0) {
            icon_right.setImageResource(icon_rightID);
        } else {
            throw new RuntimeException("控件不能为空");
        }


    }

    /**
     * 初始化标题栏
     *
     * @param icon_leftID   左边图片ID
     * @param titleID       标题ID
     * @param str_right     右边显示的字符串
     * @param leftListener  左边控件监听事件
     * @param rightListener 右边控件监听事件
     */
    public void initTitleBar(int icon_leftID, int titleID, String str_right, OnClickListener leftListener, OnClickListener rightListener
    ) {
        if (icon_leftID > 0) {
            icon_left.setImageResource(icon_leftID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (TextUtils.isEmpty(str_right)) {
            throw new RuntimeException("右边字符不能为空");
        } else {
            tv_right.setText(str_right);
            tv_right.setVisibility(VISIBLE);
        }
        if (titleID > 0) {
            tv_title.setText(getResources().getString(titleID));
        } else {
            throw new RuntimeException("标题不能为空");
        }

        if (null == leftListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_left.setOnClickListener(leftListener);
        }

        if (null == rightListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_right.setOnClickListener(rightListener);
        }
    }


    /**
     * 初始化标题栏
     *
     * @param icon_leftID   左边图片ID
     * @param title         标题
     * @param icon_rightID  右边图片ID
     * @param leftListener  左边控件监听事件
     * @param rightListener 右边控件监听事件
     */
    public void initTitleBar(Integer icon_leftID, String title, Integer icon_rightID, OnClickListener leftListener, OnClickListener rightListener
    ) {
        if (icon_leftID != null && icon_leftID > 0) {
            icon_left.setImageResource(icon_leftID);
        } else {
            icon_left.setVisibility(INVISIBLE);
//            throw new RuntimeException("控件不能为空");
        }
        if (icon_leftID != null && icon_rightID > 0) {
            icon_right.setImageResource(icon_rightID);
        } else {
            icon_right.setVisibility(INVISIBLE);
//            throw new RuntimeException("控件不能为空");
        }
        if (null == title || title.length() == 0) {
            throw new RuntimeException("标题不能为空");
        } else {
            tv_title.setText(title);
        }
//        if (null == title || title.length() == 0) {
//            tv_title.setText("");
//           throw new RuntimeException("标题不能为空");
//        } else {
//            tv_title.setText(title);
//        }

        if (null == leftListener) {
//            throw new RuntimeException("监听不能为空");
        } else {
            rl_left.setOnClickListener(leftListener);
        }

        if (null == rightListener) {
//            throw new RuntimeException("监听不能为空");
        } else {
            rl_right.setOnClickListener(rightListener);
        }
    }

    /**
     * 初始化标题栏
     *
     * @param icon_leftID    左边图片ID
     * @param title          标题
     * @param icon_rightID   右边图片ID
     * @param leftListener   左边控件监听事件
     * @param centerListener 中间监听事件
     * @param rightListener  右边控件监听事件
     */
    public void initTitleBar(int icon_leftID, String title, int icon_rightID, OnClickListener leftListener, OnClickListener centerListener, OnClickListener rightListener
    ) {
        if (icon_leftID > 0) {
            icon_left.setImageResource(icon_leftID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (icon_rightID > 0) {
            icon_right.setImageResource(icon_rightID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (null == title || title.length() == 0) {
            throw new RuntimeException("标题不能为空");
        } else {
            tv_title.setText(title);
        }

        if (null == leftListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_left.setOnClickListener(leftListener);
        }
        if (null == centerListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_center.setOnClickListener(centerListener);
        }

        if (null == rightListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_right.setOnClickListener(rightListener);
        }
    }

    /**
     * 初始化标题栏
     *
     * @param icon_leftID   左边图片ID
     * @param icon_rightID  右边图片ID
     * @param leftListener  左边控件监听事件
     * @param rightListener 右边控件监听事件
     */
    public void initTitleBar(int icon_leftID, int icon_rightID, OnClickListener leftListener, OnClickListener rightListener
    ) {
        if (icon_leftID > 0) {
            icon_left.setImageResource(icon_leftID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (icon_rightID > 0) {
            icon_right.setImageResource(icon_rightID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (null == leftListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_left.setOnClickListener(leftListener);
        }

        if (null == rightListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_right.setOnClickListener(rightListener);
        }
    }

    /**
     * 初始化标题栏
     *
     * @param icon_leftID   左边图片ID
     * @param titleID       标题ID
     * @param icon_rightID  右边图片ID
     * @param rightListener 右边控件监听事件
     */
    public void initTitleBar(int icon_leftID, int titleID, int icon_rightID, OnClickListener rightListener
    ) {
        if (icon_leftID > 0) {
            icon_left.setImageResource(icon_leftID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (icon_rightID > 0) {
            icon_right.setImageResource(icon_rightID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (titleID > 0) {
            tv_title.setText(getResources().getString(titleID));
        } else {
            throw new RuntimeException("标题不能为空");
        }


        if (null == rightListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_right.setOnClickListener(rightListener);
        }
    }

    /**
     * 初始化标题栏
     *
     * @param icon_leftID   左边图片ID
     * @param title         标题
     * @param icon_rightID  右边图片ID
     * @param rightListener 右边控件监听事件
     */
    public void initTitleBar(int icon_leftID, String title, int icon_rightID, OnClickListener rightListener
    ) {
        if (icon_leftID > 0) {
            icon_left.setImageResource(icon_leftID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (icon_rightID > 0) {
            icon_right.setImageResource(icon_rightID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (null == title || title.length() == 0) {
            throw new RuntimeException("标题不能为空");
        } else {
            tv_title.setText(title);
        }


        if (null == rightListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_right.setOnClickListener(rightListener);
        }
    }


    /**
     * 初始化标题栏
     *
     * @param icon_leftID  左边图片ID
     * @param title        标题
     * @param leftListener 左边控件监听事件
     */
    public void initTitleBar(int icon_leftID, String title, OnClickListener leftListener
    ) {
        rl_right.setVisibility(View.INVISIBLE);
        if (icon_leftID > 0) {
            icon_left.setImageResource(icon_leftID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (null == title || title.length() == 0) {
            throw new RuntimeException("标题不能为空");
        } else {
            tv_title.setText(title);
        }

        if (null == leftListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_left.setOnClickListener(leftListener);
        }
    }

    /**
     * 初始化标题栏
     *
     * @param title         标题
     * @param icon_rightID  右边图片ID
     * @param rightListener 右边控件监听事件
     */
    public void initTitleBar(String title, int icon_rightID, OnClickListener rightListener
    ) {
        rl_left.setVisibility(View.INVISIBLE);
        if (icon_rightID > 0) {
            icon_right.setImageResource(icon_rightID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (null == title || title.length() == 0) {
            throw new RuntimeException("标题不能为空");
        } else {
            tv_title.setText(title);
        }

        if (null == rightListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_right.setOnClickListener(rightListener);
        }
    }

    /**
     * 初始化标题栏
     *
     * @param title 标题
     */
    public void initTitleBar(String title) {
        rl_left.setVisibility(View.INVISIBLE);
        rl_right.setVisibility(View.INVISIBLE);
        if (null == title || title.length() == 0) {
            throw new RuntimeException("标题不能为空");
        } else {
            tv_title.setText(title);
        }
    }


    /**
     * 初始化标题栏
     *
     * @param icon_leftID  左边图片ID
     * @param titleID      标题ID
     * @param icon_rightID 右边图片ID
     */
    public void initTitleBar(int icon_leftID, int titleID, int icon_rightID
    ) {
        if (icon_leftID > 0) {
            icon_left.setImageResource(icon_leftID);
        } else {
            throw new RuntimeException("控件不能为空");
        }
        if (icon_rightID > 0) {
            icon_right.setImageResource(icon_rightID);
        } else {
            throw new RuntimeException("控件不能为空");
        }

        if (titleID > 0) {
            tv_title.setText(getResources().getString(titleID));
        } else {
            throw new RuntimeException("标题不能为空");
        }
    }


    /**
     * 初始化标题栏
     *
     * @param titleID 标题ID
     */
    public void initTitleBar(int titleID
    ) {
        rl_left.setVisibility(View.INVISIBLE);
        rl_right.setVisibility(View.INVISIBLE);
        if (titleID > 0) {
            tv_title.setText(getResources().getString(titleID));
        } else {
            throw new RuntimeException("标题不能为空");
        }
    }

    /**
     * 设置标题
     *
     * @param titleID 标题ID
     */
    public void setTitle(int titleID) {
        if (titleID > 0) {
            tv_title.setText(getResources().getString(titleID));
        } else {
            throw new RuntimeException("标题不能为空");
        }
    }

    /**
     * 设置监听
     *
     * @param listener 右边控件监听
     */
    public void setLeftListener(OnClickListener listener) {
        if (null == listener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_left.setOnClickListener(listener);
        }
    }

    /**
     * 设置监听
     *
     * @param listener 右边控件监听
     */
    public void setRightListener(OnClickListener listener) {
        if (null == listener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_right.setOnClickListener(listener);
        }
    }

    /**
     * 设置监听
     *
     * @param leftListener  左边控件监听
     * @param rightListener 右边控件监听
     */
    public void setRightListener(OnClickListener leftListener, OnClickListener rightListener) {
        if (null == leftListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_left.setOnClickListener(leftListener);
        }
        if (null == rightListener) {
            throw new RuntimeException("监听不能为空");
        } else {
            rl_right.setOnClickListener(rightListener);
        }
    }

    /**
     * 设置标题栏颜色
     *
     * @param color 16进制
     */
    public void setBackgroundColor(int color) {
        rl_titlebar.setBackgroundColor(color);
    }

    /**
     * 设置字体颜色
     *
     * @param color 16进制
     */
    public void setTitleTextColor(int color) {
        tv_title.setTextColor(color);
    }

    /**
     * 设置字体颜色
     *
     * @param color 16进制
     */
    public void setRightTextColor(int color) {
        tv_right.setTextColor(color);
    }

    public String getRightText() {
        return tv_right.getText().toString();
    }

    /**
     * 设置右边文字
     *
     * @param str
     */
    public void setRightText(String str) {
        tv_right.setText(str);
    }

    /**
     * 设置右边图片ID
     *
     * @param icon_rightID
     */
    public void setRightIcon(int icon_rightID) {
        icon_right.setImageResource(icon_rightID);
    }

    public ImageView getIcon_left() {
        return icon_left;
    }

    /**
     * 获取中间标题TextView
     *
     * @return
     */
    public TextView getTv_title() {
        return tv_title;
    }

    /**
     * 获取右边TextView
     *
     * @return
     */
    public TextView getTv_right() {
        return tv_right;
    }

    /**
     * 获取右边ImageView
     *
     * @return
     */
    public ImageView getIcon_right() {
        return icon_right;
    }

    /**
     * 获取整个布局
     *
     * @return
     */
    public RelativeLayout getRl_titlebar() {
        return rl_titlebar;
    }

    /**
     * 获取左边RelativeLayout
     *
     * @return
     */
    public RelativeLayout getRl_left() {
        return rl_left;
    }

    /**
     * 获取中间RelativeLayout
     *
     * @return
     */
    public RelativeLayout getRl_center() {
        return rl_center;
    }

    /**
     * 获取右边RelativeLayout
     *
     * @return
     */
    public RelativeLayout getRl_right() {
        return rl_right;
    }
}
