package com.xinluhuang.zhihudaily.module;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinluhuang.zhihudaily.R;
import com.xinluhuang.zhihudaily.retrofit.bean.LatestBean;
import com.xinluhuang.zhihudaily.widget.Banner;

import java.util.List;

public class ZHAdapter extends MutiAdapter<LatestBean,MutiViewHolder> {


    public ZHAdapter(Context context) {
        super(context);
    }

    public ZHAdapter(Context context, LatestBean data) {
        super(context, data);
    }

    @Override
    protected MutiViewHolder onCreateNormalHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new, parent, false);//不能添加到根视图
        return new MutiViewHolder(view);
    }

    @Override
    protected MutiViewHolder onCreateHeadHolder(@NonNull ViewGroup parent) {
        Banner banner = (Banner) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false);
        return new MutiViewHolder(banner);
    }

    @Override
    protected void onBindItemView(@NonNull MutiViewHolder holder, List<LatestBean> list, int position) {
//        View view = LayoutInflater.from(pare).inflate(R.layout.item_new, parent, false);//不能添加到根视图
//        return new NewsAdapter.ViewHolder(view);
    }

    @Override
    protected void onBindHeadView(View headView, List<LatestBean> dataList) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
