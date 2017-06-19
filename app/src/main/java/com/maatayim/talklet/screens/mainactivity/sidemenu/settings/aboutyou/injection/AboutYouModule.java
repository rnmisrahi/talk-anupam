package com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou.injection;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou.AboutYouContract;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou.AboutYouPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class AboutYouModule {

    private final AboutYouContract.View view;

    public AboutYouModule(AboutYouContract.View view) {
        this.view = view;
    }

    @Provides
    public AboutYouContract.Presenter providePresenter(AboutYouContract.View view, BaseContract.Repository repo, Scheduler scheduler) {
        return new AboutYouPresenter(view, repo, scheduler);
    }


    @Provides
    public AboutYouContract.View provideView() {
        return view;
    }

}