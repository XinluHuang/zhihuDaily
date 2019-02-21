package com.xinluhuang.zhihudaily.retrofit;


import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    public static final String BASE_URL = "http://news-at.zhihu.com/api/4/";

    public static ZhihuApi getZhihuApi() {
        OkHttpClient client=new OkHttpClient.Builder()
                .cookieJar(new MyCookieJar())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ZhihuApi.class);
    }
}
