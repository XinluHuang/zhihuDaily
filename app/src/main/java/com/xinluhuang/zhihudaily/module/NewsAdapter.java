package com.xinluhuang.zhihudaily.module;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xinluhuang.mylibrary.util.DateUtil;
import com.xinluhuang.mylibrary.util.LogUtil;
import com.xinluhuang.zhihudaily.util.Constants;
import com.xinluhuang.zhihudaily.retrofit.bean.LatestBean;
import com.xinluhuang.zhihudaily.R;
import com.xinluhuang.zhihudaily.widget.Banner;
import com.xinluhuang.zhihudaily.widget.ZHBanner;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Context context;
    private List<LatestBean> latestBeanList;
    private List<LatestBean.StoriesBean> list;
    private List<String> imageList;
    private static final int HEAD = 1;
    private static final int TOP = 0;//头部为轮播图
    private static final int NORMAL = 0;
    private static final int GROUP_HEAD = 0;


    public NewsAdapter(Context context, LatestBean latestBean) {
        this.context = context;
        latestBeanList=new ArrayList<>();
        latestBeanList.add(latestBean);
        this.list = latestBean.getStories();
        imageList = new ArrayList<>();
        for (int i = 0; i < latestBean.getTop_stories().size(); i++) {
            imageList.add(latestBean.getTop_stories().get(i).getImage());
        }
    }

    public void addData(LatestBean latestBean){
        latestBeanList.add(latestBean);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEAD) {
            Banner banner = (Banner) LayoutInflater.from(context).inflate(R.layout.item_banner, parent, false);
            return new ViewHolder(banner);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_new, parent, false);//不能添加到根视图
            return new ViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == TOP ? HEAD : NORMAL;//是否为头部
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        int _realPos = position - 1;//由于头部，位置偏移1
        if (position == TOP) {//头部为轮播图
            ((ZHBanner) holder.view).setList(latestBeanList.get(0).getTop_stories());
        } else {
            int _group=0;
            for(LatestBean latestBean:latestBeanList){
                if(_realPos>=latestBean.getStories().size()){
                    _realPos-=latestBean.getStories().size();
                    _group++;
                }
            }
            final int group=_group;
            final int realPos=_realPos;
            holder.textView.setText(latestBeanList.get(group).getStories().get(realPos).getTitle());
            Glide.with(context)
                    .load(latestBeanList.get(group).getStories().get(realPos).getImages().get(0))
                    .into(holder.imageView);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsContentActivity.class);
                    intent.putExtra(Constants.NEWS_ID, latestBeanList.get(group).getStories().get(realPos).getId());
                    context.startActivity(intent);
                }
            });
            if (realPos == GROUP_HEAD) {
                holder.groupHead.setVisibility(View.VISIBLE);
                if(group!=0){
                    holder.groupHead.setText(DateUtil.getDayBeforeForHead(group));
                    LogUtil.d(String.valueOf(group));
                }else {
                    holder.groupHead.setText(context.getString(R.string.daily));
                }
            }else {
                holder.groupHead.setVisibility(View.GONE);
            }
            if(latestBeanList.get(group).getStories().get(realPos).isMultipic()){
                holder.multi.setVisibility(View.VISIBLE);
            }else {
                holder.multi.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public int getItemCount() {
        int count=0;
        for(LatestBean latestBean:latestBeanList){
            count+=latestBean.getStories().size();
        }

        return count + 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        TextView groupHead;
        View view;
        View cardView;
        View multi;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            //如果是Banner，下面控件为空，但不会报错
            groupHead = itemView.findViewById(R.id.group_head);
            multi = itemView.findViewById(R.id.iv_multi);
            cardView = itemView.findViewById(R.id.cardview);
            textView = itemView.findViewById(R.id.tv);
            imageView = itemView.findViewById(R.id.iv);
        }

    }
}
