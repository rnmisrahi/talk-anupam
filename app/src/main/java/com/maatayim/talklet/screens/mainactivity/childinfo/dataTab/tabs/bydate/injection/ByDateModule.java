package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.injection;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.ByDateContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.ByDatePresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class ByDateModule {

    private final ByDateContract.View view;

    public ByDateModule(ByDateContract.View view) {
        this.view = view;
    }

    @Provides
    public ByDateContract.Presenter providePresenter(ByDateContract.View view, BaseContract.Repository repo, Scheduler scheduler) {
        return new ByDatePresenter(view, repo, scheduler);
    }


    @Provides
    public ByDateContract.View provideView() {
        return view;
    }

}