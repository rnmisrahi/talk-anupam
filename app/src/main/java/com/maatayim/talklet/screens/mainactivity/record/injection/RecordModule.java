package com.maatayim.talklet.screens.mainactivity.record.injection;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.record.RecordContract;
import com.maatayim.talklet.screens.mainactivity.record.RecordPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class RecordModule {

    private final RecordContract.View view;

    public RecordModule(RecordContract.View view) {
        this.view = view;
    }

    @Provides
    public RecordContract.Presenter providePresenter(RecordContract.View view,
                                                     BaseContract.Repository repo,
                                                     Scheduler scheduler) {
        return new RecordPresenter(view, repo, scheduler);
    }


    @Provides
    public RecordContract.View provideView() {
        return view;
    }

}