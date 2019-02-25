package com.example.a0220.data.utils;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

public interface BaseApis {

    @POST
    Observable<ResponseBody> post(@Url String url);
}
