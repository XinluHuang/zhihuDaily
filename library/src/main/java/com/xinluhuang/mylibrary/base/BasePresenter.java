package com.xinluhuang.mylibrary.base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M,V> {
    protected M mModel;
    protected WeakReference<V> mViewRef;

    protected V getView(){
        return isAttached()?mViewRef.get():null;
    }

    protected boolean isAttached(){
        return null!= mViewRef && null!=mViewRef.get();
    }

    protected void onDetach(){
        if(mViewRef!=null){
            mViewRef.clear();
            mViewRef=null;
        }
    }

    public void attachModelView(M model, V pView) {
        mViewRef=new WeakReference<>(pView);
        mModel=model;
    }
}