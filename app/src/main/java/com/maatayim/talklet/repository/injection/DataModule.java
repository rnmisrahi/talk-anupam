package com.maatayim.talklet.repository.injection;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.repository.LocalData;
import com.maatayim.talklet.repository.RemoteData;
import com.maatayim.talklet.repository.RepositoryImpl;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Sophie on 5/30/2017.
 */

@Module
public class DataModule {


    @Singleton
    @Provides
    public LocalData provideLocalData() {
//        return new LocalDataImpl(context);
        return new LocalData();
    }

    @Singleton
    @Provides
    public RemoteData provideRemoteData() {
//        return new RemoteDataImpl(context);
        return new RemoteData();
    }

    @Singleton
    @Provides
    public BaseContract.Repository provideMainRepository(LocalData localDataImpl, RemoteData remoteData) {
        return new RepositoryImpl(localDataImpl, remoteData);
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .build();
        return retrofit;
    }


}
