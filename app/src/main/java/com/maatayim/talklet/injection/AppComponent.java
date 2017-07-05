package com.maatayim.talklet.injection;

import com.maatayim.talklet.LoginActivity;
import com.maatayim.talklet.MainActivity;
import com.maatayim.talklet.MainActivityComponent;
import com.maatayim.talklet.MainActivityModule;
import com.maatayim.talklet.repository.injection.DataModule;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.injection.ByDateComponent;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.injection.ByDateModule;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.byrecording.injection.ByRecordingComponent;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.byrecording.injection.ByRecordingModule;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.injection.GeneralTabComponent;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.injection.GeneralTabModule;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.injection.DataTabComponent;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.injection.DataTabModule;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.injection.FavoriteWordsComponent;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.injection.FavoriteWordsModule;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.injection.ChildMainComponent;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.injection.ChildMainModule;
import com.maatayim.talklet.screens.mainactivity.childinfo.injection.ChildComponent;
import com.maatayim.talklet.screens.mainactivity.childinfo.injection.ChildModule;
import com.maatayim.talklet.screens.mainactivity.mainscreen.dagger.MainComponent;
import com.maatayim.talklet.screens.mainactivity.mainscreen.dagger.MainModule;
import com.maatayim.talklet.screens.loginactivity.login.injection.LoginComponent;
import com.maatayim.talklet.screens.loginactivity.login.injection.LoginModule;
import com.maatayim.talklet.screens.loginactivity.signup.choosephoto.dagger.ChoosePhotoComponent;
import com.maatayim.talklet.screens.loginactivity.signup.choosephoto.dagger.ChoosePhotoModule;
import com.maatayim.talklet.screens.loginactivity.signup.dagger.SignupComponent;
import com.maatayim.talklet.screens.loginactivity.signup.dagger.SignupModule;
import com.maatayim.talklet.screens.mainactivity.record.injection.RecordComponent;
import com.maatayim.talklet.screens.mainactivity.record.injection.RecordModule;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutbabyrv.aboutbabyf.injection.AboutBabyComponent;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutbabyrv.aboutbabyf.injection.AboutBabyModule;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou.injection.AboutYouComponent;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou.injection.AboutYouModule;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.injection.SettingComponent;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.injection.SettingModule;

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

//    MainActivityComponent plus(MainActivityModule mainActivityModule);

    ChoosePhotoComponent plus(ChoosePhotoModule module);

    MainComponent plus(MainModule module);

    SignupComponent plus(SignupModule module);

    LoginComponent plus(LoginModule module);

    ChildComponent plus(ChildModule childModule);

    ChildMainComponent plus(ChildMainModule childGeneralModule);

    GeneralTabComponent plus(GeneralTabModule generalTabModule);

    DataTabComponent plus(DataTabModule dataTabModule);

    ByDateComponent plus(ByDateModule byDateModule);

    SettingComponent plus(SettingModule settingModule);

    AboutYouComponent plus(AboutYouModule aboutYouModule);

    ByRecordingComponent plus(ByRecordingModule byRecordingModule);

    FavoriteWordsComponent plus(FavoriteWordsModule favoriteWordsModule);

    AboutBabyComponent plus(AboutBabyModule aboutBabyModule);

    RecordComponent plus(RecordModule recordModule);


}

