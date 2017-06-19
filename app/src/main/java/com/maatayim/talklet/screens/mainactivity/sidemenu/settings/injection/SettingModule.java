package com.maatayim.talklet.screens.mainactivity.sidemenu.settings.injection;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.SettingContract;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.SettingPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class SettingModule {

    private final SettingContract.View view;

    public SettingModule(SettingContract.View view) {
        this.view = view;
    }

    @Provides
    public SettingContract.Presenter providePresenter(SettingContract.View view, BaseContract.Repository repo, Scheduler scheduler) {
        return new SettingPresenter(view, repo, scheduler);
    }


    @Provides
    public SettingContract.View provideView() {
        return view;
    }

}