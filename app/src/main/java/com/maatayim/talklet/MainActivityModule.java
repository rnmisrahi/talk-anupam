package com.maatayim.talklet;

import com.maatayim.talklet.baseline.BaseContract;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class MainActivityModule {

    private final MainActivityContract.View view;

    public MainActivityModule(MainActivityContract.View view) {
        this.view = view;
    }

    @Provides
    public MainActivityContract.Presenter providePresenter(MainActivityContract.View view, BaseContract.Repository repo, Scheduler scheduler) {
        return new MainActivityPresenter(view, repo, scheduler);
    }


    @Provides
    public MainActivityContract.View provideView() {
        return view;
    }

}