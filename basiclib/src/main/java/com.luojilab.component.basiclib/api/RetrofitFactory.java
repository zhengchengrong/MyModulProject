package com.luojilab.component.basiclib.api;

import android.text.TextUtils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.luojilab.component.basiclib.utils.RxLogTool;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhengchengrong on 2017/9/3.
 */

public class RetrofitFactory {
   public static String BASE_URL = "http://hhhmis.f3322.net:8013/supervise_app_bjaj/";
    public static final int  MSG_SUCESS=1, MSG_FAIL=0,MSG_OTHER=2;
    // 设置超时
    private static final long TIMEOUT = 30;

    // Retrofit是基于OkHttpClient的，可以创建一个OkHttpClient进行一些配置
    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            // 添加通用的Header
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder builder = chain.request().newBuilder();
                 /*   String token = SPUtils.get(AndroidApplication.getAppContext(), GlobalConstant.TOKEN,"").toString();
                   if(!TextUtils.isEmpty(token)){
                       builder.addHeader("token", token); // 添加token
                   }
                    String loginId = SPUtils.get(AndroidApplication.getAppContext(),GlobalConstant.LOGINID,"").toString();
                    if(!TextUtils.isEmpty(loginId)){
                        builder.addHeader("loginId", loginId); // 添加loginId
                    }*/
                    return chain.proceed(builder.build());
                }
            })
            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    RxLogTool.d(message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build();

    private static BjajService retrofitService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            // 添加Gson转换器
            .addConverterFactory(GsonConverterFactory.create(buildGson()))
            // 添加Retrofit到RxJava的转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(BjajService.class);

    // 得到实例
    public static BjajService getInstance() {
        return retrofitService;
    }

    private static Gson buildGson() {
        return new GsonBuilder()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();
    }
}

