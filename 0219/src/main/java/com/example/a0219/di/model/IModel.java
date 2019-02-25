package com.example.a0219.di.model;

import com.example.a0219.di.callback.MyCallBack;

public interface IModel {

    void startRequest(String url, Class clazz, MyCallBack callBack);
}
