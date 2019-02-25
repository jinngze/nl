package com.example.dell.nl.di.model;


import com.example.dell.nl.di.callback.MyCallBack;

public interface IModel {
    void mRequestData(String urlStr, Class clazz, MyCallBack myCallBack);
}
