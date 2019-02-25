package com.example.a0220.di.model;

import com.example.a0220.data.utils.RetrofitManager;
import com.example.a0220.di.callback.MyCallBack;
import com.google.gson.Gson;

public class IModelImpl implements IModel {


    @Override
    public void mRequestData(String urlStr, final Class clazz, final MyCallBack myCallBack) {
        RetrofitManager.getRetrofitManager().post(urlStr, new RetrofitManager.HttpListener() {
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
