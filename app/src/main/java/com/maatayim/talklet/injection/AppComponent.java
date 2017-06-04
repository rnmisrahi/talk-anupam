package com.maatayim.talklet.injection;

import com.maatayim.talklet.LoginActivity;
import com.maatayim.talklet.MainActivity;
import com.maatayim.talklet.repository.injection.DataModule;
import com.maatayim.talklet.screens.mainscreen.dagger.GeneralComponent;
import com.maatayim.talklet.screens.mainscreen.dagger.GeneralModule;
import com.maatayim.talklet.screens.mainscreen.generalticket.dagger.TipComponent;
import com.maatayim.talklet.screens.mainscreen.generalticket.dagger.TipModule;
import com.maatayim.talklet.screens.loginactivity.login.injection.LoginComponent;
import com.maatayim.talklet.screens.loginactivity.login.injection.LoginModule;
import com.maatayim.talklet.screens.loginactivity.signup.choosephoto.dagger.ChoosePhotoComponent;
import com.maatayim.talklet.screens.loginactivity.signup.choosephoto.dagger.ChoosePhotoModule;
import com.maatayim.talklet.screens.loginactivity.signup.dagger.SignupComponent;
import com.maatayim.talklet.screens.loginactivity.signup.dagger.SignupModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sophie on 6/1/2017.
 */


@Singleton
@Component(modules = {AppModule.class, DataModule.class, TalkletModule.class})
public interface AppComponent {

    void inject(LoginActivity target);

    void inject(MainActivity target);


    ChoosePhotoComponent plus(ChoosePhotoModule module);

    GeneralComponent plus(GeneralModule module);

    SignupComponent plus(SignupModule module);

    LoginComponent plus(LoginModule module);

    TipComponent plus(TipModule module);


}

