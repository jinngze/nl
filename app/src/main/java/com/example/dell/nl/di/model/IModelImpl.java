package com.example.dell.nl.di.model;

import com.example.dell.nl.data.utils.RetrofitManager;
import com.example.dell.nl.di.callback.MyCallBack;
import com.google.gson.Gson;


import retrofit2.Retrofit;

public class IModelImpl implements IModel{


    @Override
    public void mRequestData(String urlStr, final Class clazz, final MyCallBack myCallBack) {
        RetrofitManager.getRetrofitManager().get(urlStr, new RetrofitManager.HttpListener() {
            @Override
            public void success(String data) {
                Object o = new Gson().fromJson(data, clazz);
                if (myCallBack != null){
                    myCallBack.OnSuccess(o);
                }
            }

            @Override
            public void fails(String error) {
                if (myCallBack != null){
                    myCallBack.OnFails(error);
                }
            }
        });
    }

}
