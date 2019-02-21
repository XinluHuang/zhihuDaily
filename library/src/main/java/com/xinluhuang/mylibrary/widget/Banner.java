package com.xinluhuang.mylibrary.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;


public abstract class Banner<T> extends RelativeLayout implements ViewPager.OnPageChangeListener, View.OnTouchListener {
    private List<T> list;
    private ViewPager viewPager;
    private LinearLayout linearLayout;//底部原点指示
    int prePosition = 0;//指向图片第一个
    private final static long DELAY = 3000;
    private PagerAdapter pagerAdapter;
    boolean b = false;
    private final static int DIAMETER=10;//原点直径

    //循环图片
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            handler.sendEmptyMessageDelayed(0, DELAY);
        }
    };

    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {


        viewPager = new ViewPager(getContext());
        LayoutParams vpParam = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(viewPager, vpParam);
        viewPager.setOnPageChangeListener(this);
        pagerAdapter = new BannerPagerAdapter();
        viewPager.setAdapter(pagerAdapter);

        //圆点指示,必须在viewpager后添加，否则覆盖看不见
        linearLayout = new LinearLayout(getContext());
        LayoutParams llParam = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llParam.addRule(ALIGN_PARENT_BOTTOM);
        llParam.addRule(CENTER_HORIZONTAL);
        llParam.bottomMargin = 30;
        addView(linearLayout, llParam);
    }

    public void setList(List<T> l) {
        this.list = l;
        if(l==null){
            return;
        }
        handler.removeCallbacksAndMessages(null);//handler清空任务

        prePosition = 0;
        linearLayout.removeAllViews();
        addIndicator(this.list.size());
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % this.list.size());//要放到后面，否则不起作用，会从零开始
        handler.sendEmptyMessageDelayed(0, DELAY);
    }

    private void addIndicator(int size) {
        for (int i = 0; i < size; i++) {
            ImageView point = new ImageView(getContext());
            point.setBackground(getSelectorBackground());
            LinearLayout.LayoutParams pointLayoutParams = new LinearLayout.LayoutParams(DIAMETER, DIAMETER);
            if (i == 0) {
                point.setEnabled(true);
            } else if (i != 0) {
                pointLayoutParams.leftMargin = DIAMETER;//圆点间隔
                point.setEnabled(false);
            }
            linearLayout.addView(point, pointLayoutParams);
        }
    }

    //动态生成selector选择器
    private Drawable getSelectorBackground() {
        GradientDrawable enable = new GradientDrawable();
        enable.setColor(Color.parseColor("#ffffff"));
        enable.setShape(GradientDrawable.OVAL);
        GradientDrawable normal = new GradientDrawable();
        normal.setColor(Color.parseColor("#888888"));
        normal.setShape(GradientDrawable.OVAL);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, enable);
        stateListDrawable.addState(new int[]{}, normal);
        return stateListDrawable;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        position = position % list.size();
        //先设置false，再设置true，防止当pre和position都为零和position都为零，即初始化时的例外
        linearLayout.getChildAt(this.prePosition).setEnabled(false);
        linearLayout.getChildAt(position).setEnabled(true);
        this.prePosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //按下则不会自动跳页的设置
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //b用来判断是否消费此事件,true为消费，事件不会继续分发
        b = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handler.removeCallbacksAndMessages(null);
                break;
            case MotionEvent.ACTION_MOVE:
                b = true;
                break;
            case MotionEvent.ACTION_CANCEL:
                handler.sendEmptyMessageDelayed(0, DELAY);
                break;
            case MotionEvent.ACTION_UP:
                handler.sendEmptyMessageDelayed(0, DELAY);
                break;
            default:
                break;
        }
        return b;
    }

    public View instantiateItem(@NonNull ViewGroup container, int position) {
        View view = instantiateItem(container, position % list.size(),list);
        view.setOnTouchListener(this);
        return view;
    }

    @NonNull
    public abstract View instantiateItem(@NonNull ViewGroup container, int position,List<T> list);

    class BannerPagerAdapter extends PagerAdapter {
        public BannerPagerAdapter() {

        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view=Banner.this.instantiateItem(container, position);
            container.addView(view);//容易忘记，要添加
            return view;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }

}
