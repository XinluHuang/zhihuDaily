package com.xinluhuang.zhihudaily.module.main;

import com.xinluhuang.mylibrary.base.BaseModel;
import com.xinluhuang.mylibrary.base.BasePresenter;
import com.xinluhuang.mylibrary.base.BaseView;
import com.xinluhuang.zhihudaily.retrofit.bean.LatestBean;

public interface MainContract {
    interface IMainView extends BaseView{
        void refresh(LatestBean latestBean);
        void addData(LatestBean latestBean);
    }
    interface IMainModel extends BaseModel{
        public void getBeforeData(int dayBefore, final MVPListener<LatestBean> listener) ;
        public void getTodayData(final MVPListener<LatestBean> listener) ;
    }
    abstract class IMainPresenter extends BasePresenter<IMainModel,IMainView>{

    }
}
