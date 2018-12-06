package com.xinluhuang.zhihudaily.module;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class MutiAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private View headView;
    private List<T> dataList;
    private Context context;
    public static final int HEAD = 1;
    public static final int TOP = 0;//头部为轮播图
    public static final int NORMAL = 0;
    public static final int GROUP_HEAD = 0;

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VH vh = null;
        if (viewType == HEAD) {
            vh = onCreateHeadHolder(parent);
        } else {
            vh = onCreateNormalHolder(parent);
        }
        return vh;
    }

    public Context getContext() {
        return context;
    }

    protected abstract VH onCreateNormalHolder(@NonNull ViewGroup parent);

    protected abstract VH onCreateHeadHolder(@NonNull ViewGroup parent);


    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        int realPosition = position;
        int group = 0;
        if (headView != null) {
            realPosition--;
            if (position == TOP) {//头部为轮播图
                onBindHeadView(headView, dataList);
            }
        } else {
            onBindItemView(holder, dataList, realPosition);
        }
    }

    protected abstract void onBindItemView(@NonNull VH holder, List<T> list, int position);

    protected abstract void onBindHeadView(View headView, List<T> dataList);

    @Override
    public int getItemViewType(int position) {
        return position == TOP ? HEAD : NORMAL;//是否为头部
    }

    /*@Override
    public int getItemCount() {
        int count = 0;
        for (List<T> list : groupList) {
            count += list.size();
        }
        return headView == null ? count + 1 : count;
    }*/

    public MutiAdapter(Context context) {
        this.context = context;
        this.dataList = new ArrayList<T>();
    }

    public MutiAdapter(Context context, T data) {
        this(context);
        addMoreData(data);
    }

    public void addMoreData(T data) {
        dataList.add(data);
    }

    public View getHeadView() {
        return headView;
    }

    public void setHeadView(View headView) {
        this.headView = headView;
    }

    public void removeHeadView() {
        this.headView = null;
    }
}
