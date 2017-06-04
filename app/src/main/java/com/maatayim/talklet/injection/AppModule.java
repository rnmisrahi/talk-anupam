package com.maatayim.talklet.injection;

import android.app.Application;
import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Sophie on 6/1/2017
 */

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    /**
     * The method names for the providers,
     * such as provideContext(),
     * are not important and can be named anything you like.
     * Dagger only looks at the return type.
     */
    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }


}
