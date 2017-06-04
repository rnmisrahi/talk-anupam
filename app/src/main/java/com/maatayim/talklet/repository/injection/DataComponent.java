package com.maatayim.talklet.repository.injection;

import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by Sophie on 5/30/2017
 */


@Singleton
@Component(modules={DataModule.class})
public interface  DataComponent {

//    Retrofit retrofit();
//    OkHttpClient okHttpClient();
//    SharedPreferences sharedPreferences();
}
