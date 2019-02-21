package com.xinluhuang.zhihudaily.module.main;

import com.xinluhuang.zhihudaily.retrofit.bean.LatestBean;

public class MainPresenter extends MainContract.IMainPresenter {
    private int dayBefore=0;
    public void addData(){
        final MainContract.IMainView view=getView();
        if(view==null){
            return;
        }
        mModel.getBeforeData(dayBefore++, new MVPListener<LatestBean>() {
            @Override
            public void onSuccess(LatestBean data) {
                view.addData(data);
                view.hideLoading();
            }

            @Override
            public void onError() {
                view.showError();
            }
        });
    }

    public void refresh(){
        final MainContract.IMainView view=getView();

        if(view==null){
            return;
        }
        mModel.getTodayData(new MVPListener<LatestBean>() {
            @Override
            public void onSuccess(LatestBean data) {
                view.refresh(data);
                view.hideLoading();
            }

            @Override
            public void onError() {
                view.showError();
            }
        });
    }
}
