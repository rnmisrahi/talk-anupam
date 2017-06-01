package com.maatayim.talklet.application;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
