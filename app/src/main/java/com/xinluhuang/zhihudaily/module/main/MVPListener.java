package com.xinluhuang.zhihudaily.module.main;

public interface MVPListener<E> {
    void onSuccess(E data);
    void onError();
}
