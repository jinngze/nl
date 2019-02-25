package com.example.dell.nl.data.utils;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface BaseApis {

    @GET
    Observable<ResponseBody> get(@Url String url);
}
