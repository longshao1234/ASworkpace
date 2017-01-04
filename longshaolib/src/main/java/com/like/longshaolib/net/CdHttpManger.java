package com.like.longshaolib.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by longshao on 2016/6/20 0020.
 * (网络请求方法都写在这里面，也可以继承改方法)
 */

public abstract class CdHttpManger {

    public abstract String getBaseUrl();

    private static final int DEFAULT_TIMEOUT = 5;
    protected Retrofit retrofit;

    public CdHttpManger(){
       OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit=new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(getBaseUrl())
                .build();
    }
}
