package com.example.a0220.di.presenter;


import com.example.a0220.di.callback.MyCallBack;
import com.example.a0220.di.model.IModelImpl;
import com.example.a0220.di.view.IView;

public class IPresenterImpl implements IPresenter {
    private IView iView;
    private IModelImpl iModel;

    public IPresenterImpl(IView iView) {
        this.iView = iView;
        iModel = new IModelImpl();
    }

    @Override
    public void pRequestData(String urlStr, Class clazz) {
        iModel.mRequestData(urlStr, clazz, new MyCallBack() {
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
