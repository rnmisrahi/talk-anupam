package com.maatayim.talklet.repository.retrofit.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.maatayim.talklet.repository.LocalData;
import com.maatayim.talklet.repository.retrofit.model.children.ChildrenListWrapper;
import com.maatayim.talklet.screens.Child;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Sophie on 6/29/2017
 */

public class BackEndApi {

    private static String BASE_URL = "http://wxsvgtwqbqm7xtbwc-mock.stoplight-proxy.io/api/v1/";
    private static RetrofitApi instance;


    public static RetrofitApi getApi() {
        if (instance == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(logging);

            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory
                                    .createWithScheduler(Schedulers.io()))
                    .client(httpClient.build())
                    .build();
            instance = retrofit.create(RetrofitApi.class);
        }
        return instance;
    }

}
