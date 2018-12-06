package com.xinluhuang.zhihudaily.retrofit;

import com.xinluhuang.zhihudaily.retrofit.bean.LatestBean;
import com.xinluhuang.zhihudaily.retrofit.bean.LongComments;
import com.xinluhuang.zhihudaily.retrofit.bean.NewsContentBean;
import com.xinluhuang.zhihudaily.retrofit.bean.NewsExtraBean;
import com.xinluhuang.zhihudaily.retrofit.bean.ShortComments;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ZhihuApi {
    @GET("news/latest")
    Observable<LatestBean> getLatestList();

    @GET("news/{id}")
    Observable<NewsContentBean> getContent(@Path("id") int id);

    //评论，点赞数等消息
    @GET("story-extra/{id}")
    Observable<NewsExtraBean> getExtra(@Path("id") int id);

    @GET("story/{id}/long-comments")
    Observable<LongComments> getLongComments(@Path("id") int id);

    @GET("story/{id}/short-comments")
    Observable<ShortComments> getShortComments(@Path("id") int id);

    //可以共用LatestBean，格式相似
    @GET("news/before/{date}")
    Observable<LatestBean> getBeforeList(@Path("date") String date);
}
