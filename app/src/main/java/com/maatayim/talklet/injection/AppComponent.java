package com.maatayim.talklet.injection;

import com.maatayim.talklet.LoginActivity;
import com.maatayim.talklet.MainActivity;
import com.maatayim.talklet.repository.injection.DataModule;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.injection.ByDateComponent;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.injection.ByDateModule;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.injection.GeneralTabComponent;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.injection.GeneralTabModule;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.injection.DataTabComponent;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.injection.DataTabModule;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.injection.ChildGeneralComponent;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.injection.ChildGeneralModule;
import com.maatayim.talklet.screens.mainactivity.childinfo.injection.ChildComponent;
import com.maatayim.talklet.screens.mainactivity.childinfo.injection.ChildModule;
import com.maatayim.talklet.screens.mainactivity.mainscreen.dagger.GeneralComponent;
import com.maatayim.talklet.screens.mainactivity.mainscreen.dagger.GeneralModule;
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

    ChildComponent plus(ChildModule childModule);

    ChildGeneralComponent plus(ChildGeneralModule childGeneralModule);

    GeneralTabComponent plus(GeneralTabModule generalTabModule);

    DataTabComponent plus(DataTabModule dataTabModule);

    ByDateComponent plus(ByDateModule byDateModule);
}

