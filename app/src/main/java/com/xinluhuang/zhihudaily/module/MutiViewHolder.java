package com.xinluhuang.zhihudaily.module;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinluhuang.zhihudaily.R;

public class MutiViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public ImageView imageView;
    public TextView groupHead;
    public View view;
    public View cardView;
    public MutiViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        //如果是Banner，下面控件为空，但不会报错
        groupHead=itemView.findViewById(R.id.group_head);
        cardView=itemView.findViewById(R.id.cardview);
        textView = itemView.findViewById(R.id.tv);
        imageView = itemView.findViewById(R.id.iv);
    }
}
