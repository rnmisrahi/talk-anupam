package com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutbabyrv.aboutbabyf.injection;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutbabyrv.aboutbabyf.AboutBabyPresenter;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutbabyrv.aboutbabyf.AboutBabyContract;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class AboutBabyModule {


    private final AboutBabyContract.View view;

    public AboutBabyModule(AboutBabyContract.View view) {
        this.view = view;
    }

    @Provides
    public AboutBabyContract.Presenter providePresenter(AboutBabyContract.View view, BaseContract.Repository repo, Scheduler scheduler) {
        return new AboutBabyPresenter(view, repo, scheduler);
    }


    @Provides
    public AboutBabyContract.View provideView() {
        return view;
    }

}