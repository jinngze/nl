package com.example.a0222.di.presenter;

import com.example.a0222.di.callback.MyCallBack;
import com.example.a0222.di.model.IModel;
import com.example.a0222.di.model.ShowModel;
import com.example.a0222.di.view.IView;

public class ShowPresenter implements IPresenter {

    IModel iModel;
    IView iView;

    public ShowPresenter(IView iView) {
        this.iView = iView;
        iModel = new ShowModel();
    }

    @Override
    public void startRequest(String url, Class clazz) {

        iModel.requestData(url, clazz, new MyCallBack() {
            @Override
            public void OnSuccess(Object data) {
                iView.onSuccess(data);

            }

            @Override
            public void OnFails(String error) {

            }
        });

    }



    public void onDetch(){
        if (iView != null){
            iView = null;
        }
        if (iModel != null){
            iModel = null;
        }
    }
}
