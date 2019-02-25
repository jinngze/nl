package com.example.a0222.data.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitManager {

    private static volatile RetrofitManager retrofitManager;
    private final BaseApis baseApis;

    public static RetrofitManager getRetrofitManager(){
        if (retrofitManager == null){
            synchronized (RetrofitManager.class){
                retrofitManager = new RetrofitManager();
            }
        }
        return retrofitManager;
    }

    private RetrofitManager(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //对OkHttp的支持
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true);

        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Apis.URL_Z)
                .client(client)
                .build();
        baseApis = retrofit.create(BaseApis.class);

    }

    //post请求
    public void post(String urlStr, HttpListener listener){

        baseApis.get(urlStr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(listener));
    }

    public Observer getObserver(final HttpListener listener){
        Observer observer = new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (listener != null){
                    listener.fails(e.getMessage());
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    if (listener != null){
                        listener.success(string);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    if (listener != null){
                        listener.fails(e.getMessage());
                    }
                }
            }

        };
        return observer;
    }


    HttpListener mListener;
    public void result(HttpListener listener){
        this.mListener = listener;
    }
    public interface HttpListener{
        void success(String data);
        void fails(String error);
    }

}
