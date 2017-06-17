package com.maatayim.talklet.baseline;

import android.app.Application;

import com.maatayim.talklet.R;
import com.maatayim.talklet.injection.DaggerAppComponent;
import com.maatayim.talklet.injection.AppComponent;
import com.maatayim.talklet.injection.AppModule;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Sophie on 6/1/2017
 */

public class TalkletApplication extends Application {
    private AppComponent appComponent;

    @Override public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Avenir-Medium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    protected AppComponent initDagger(TalkletApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }



//    public TalkletApplication getApplicationModule() {
//        return applicationModule;
//    }

//    public TalkletModule getMainModule(BaseContract.View view) {
//    }


}
