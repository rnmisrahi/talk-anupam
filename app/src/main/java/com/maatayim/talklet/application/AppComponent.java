package com.maatayim.talklet.application;

import com.maatayim.talklet.LoginActivity;
import com.maatayim.talklet.MainActivity;
import com.maatayim.talklet.repository.dagger.DataModule;
import com.maatayim.talklet.screens.login.dagger.LoginComponent;
import com.maatayim.talklet.screens.login.dagger.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sophie on 6/1/2017.
 */


@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {

    void inject(LoginActivity target);

    void inject(MainActivity target);

    LoginComponent plus(LoginModule module);


}

