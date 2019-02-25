package com.example.a0222.di.model;

import com.example.a0222.di.callback.MyCallBack;

public interface IModel {

    void requestData(String url, Class clazz, MyCallBack callBack);
}
