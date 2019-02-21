package com.xinluhuang.mylibrary.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xinluhuang.mylibrary.util.CreateUtil;

public abstract class BaseActivity<T extends  BasePresenter,M extends  BaseModel> extends AppCompatActivity {
    public T mPresenter;
    public M mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mPresenter= CreateUtil.getT(this,0);
        mModel= CreateUtil.getT(this,1);
        mPresenter.attachModelView(mModel,this);
        initView();
    }

    public abstract int getLayoutResId();
    public abstract void initView();
}
