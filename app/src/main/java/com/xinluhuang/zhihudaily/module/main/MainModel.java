package com.xinluhuang.zhihudaily.module.main;

import com.xinluhuang.mylibrary.util.DateUtil;
import com.xinluhuang.zhihudaily.retrofit.RetrofitHelper;
import com.xinluhuang.zhihudaily.retrofit.bean.LatestBean;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainModel implements MainContract.IMainModel {

    @Override
    public void getBeforeData(int dayBefore, final MVPListener<LatestBean> listener) {
        RetrofitHelper.getZhihuApi()
                .getBeforeList(DateUtil.getDayBefore(dayBefore))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LatestBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(LatestBean latestBean) {
                        listener.onSuccess(latestBean);
                    }
                });
    }

    @Override
    public void getTodayData(final MVPListener<LatestBean> listener) {
        RetrofitHelper.getZhihuApi()
                .getLatestList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LatestBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(LatestBean latestBean) {
                        listener.onSuccess(latestBean);
                    }
                });
    }
}
