package com.maatayim.talklet.repository.retrofit.api;

import android.util.Log;

import com.facebook.stetho.Stetho;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Sophie on 6/29/2017
 */

public class BackEndApi {

//    private static String BASE_URL_MOCK = "http://wxsvgtwqbqm7xtbwc-mock.stoplight-proxy.io/api/v1/";
    private static String BASE_URL = "http://talkjwt2.azurewebsites.net/api/v1/";
//    private static String BASE_URL = BASE_URL_MOCK;
//
    private static RetrofitApi instance;


    public static RetrofitApi getApi() {
        if (instance == null) {

            final HttpLoggingInterceptor logging =
                    new HttpLoggingInterceptor(BackEndApi::logHttpMessage)
                            .setLevel(HttpLoggingInterceptor.Level.BODY);

//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

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




    private static final String TAG = "BackEndApi";

    private static void logHttpMessage(String message) {
        if (message.length() < 10000) {
            Log.d(TAG, message);
        } else {
            Log.d(TAG, message.substring(0, 10000) +
                    "[... + " +
                    (message.length() - 10000) +
                    " chars]");
        }
    }


}
