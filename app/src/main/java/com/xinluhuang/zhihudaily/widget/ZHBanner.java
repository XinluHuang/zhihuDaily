package com.xinluhuang.zhihudaily.widget;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xinluhuang.zhihudaily.util.Constants;
import com.xinluhuang.zhihudaily.R;
import com.xinluhuang.zhihudaily.module.NewsContentActivity;
import com.xinluhuang.zhihudaily.retrofit.bean.LatestBean;

import java.util.List;

public class ZHBanner extends Banner<LatestBean.TopStoriesBean> {
    public ZHBanner(Context context) {
        this(context, null);
    }

    public ZHBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZHBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ZHBanner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @NonNull
    @Override
    public View instantiateItem(@NonNull ViewGroup container, int position,final List<LatestBean.TopStoriesBean> list) {
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.banner, container, false);
        ImageView imageView = relativeLayout.findViewById(R.id.iv_head);
        TextView textView = relativeLayout.findViewById(R.id.tv_head);
        Glide.with(getContext())
                .load(list.get(position).getImage())
                .into(imageView);
        textView.setText(list.get(position).getTitle());
        final int finalPosition = position;
        relativeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), NewsContentActivity.class);
                intent.putExtra(Constants.NEWS_ID,list.get(finalPosition).getId());
                getContext().startActivity(intent);
            }
        });
        return relativeLayout;
    }
}
