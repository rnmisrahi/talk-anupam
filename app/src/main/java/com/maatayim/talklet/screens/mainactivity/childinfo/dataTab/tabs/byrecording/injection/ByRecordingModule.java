package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.byrecording.injection;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.byrecording.ByRecordingContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.byrecording.ByRecordingPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class ByRecordingModule {

    private final ByRecordingContract.View view;

    public ByRecordingModule(ByRecordingContract.View view) {
        this.view = view;
    }

    @Provides
    public ByRecordingContract.Presenter providePresenter(ByRecordingContract.View view, BaseContract.Repository repo, Scheduler scheduler) {
        return new ByRecordingPresenter(view, repo, scheduler);
    }


    @Provides
    public ByRecordingContract.View provideView() {
        return view;
    }

}