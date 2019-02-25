package com.example.a0219.di.presenter;

import com.example.a0219.di.callback.MyCallBack;
import com.example.a0219.di.model.IModel;
import com.example.a0219.di.model.ShowModel;
import com.example.a0219.di.view.IView;

public class ShowPresenter implements IPresenter {

    private IModel iModel;
    private IView iView;

    public ShowPresenter(IView iView) {
        this.iView = iView;
        iModel = new ShowModel();
    }

    @Override
    public void requestData(String url, Class clazz) {

        iModel.startRequest(url, clazz, new MyCallBack() {
            @Override
            public void OnSuccess(Object data) {

                iView.OnSuccess(data);
            }

            @Override
            public void OnFails(String error) {

            }
        });

    }

     public  void onDetach(){

        if(iModel != null){

            iModel = null;
        }
        if(iView != null){

            iView = null;
        }
     }
}
