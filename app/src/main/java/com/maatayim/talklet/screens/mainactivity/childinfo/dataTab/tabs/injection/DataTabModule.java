package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.injection;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.DataTabContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.DataTabPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class DataTabModule {

    private final DataTabContract.View view;

    public DataTabModule(DataTabContract.View view) {
        this.view = view;
    }

    @Provides
    public DataTabContract.Presenter providePresenter(DataTabContract.View view, BaseContract.Repository repo, Scheduler scheduler) {
        return new DataTabPresenter(view, repo, scheduler);
    }


    @Provides
    public DataTabContract.View provideView() {
        return view;
    }

}