package com.example.a0220.di.model;


import com.example.a0220.di.callback.MyCallBack;

public interface IModel {
    void mRequestData(String urlStr, Class clazz, MyCallBack myCallBack);
}
