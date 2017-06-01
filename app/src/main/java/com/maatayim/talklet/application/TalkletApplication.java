package com.maatayim.talklet.application;

import android.app.Application;

/**
 * Created by Sophie on 6/1/2017.
 */

public class TalkletApplication extends Application {
    private AppComponent appComponent;

    @Override public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    protected AppComponent initDagger(TalkletApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
}
