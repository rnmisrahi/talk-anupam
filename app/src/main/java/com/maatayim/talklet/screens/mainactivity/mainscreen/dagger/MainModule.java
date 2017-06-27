package com.maatayim.talklet.screens.mainactivity.mainscreen.dagger;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.mainscreen.MainContract;
import com.maatayim.talklet.screens.mainactivity.mainscreen.MainPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by Sophie on 5/28/2017
 */

@Module
public class MainModule {

    private final MainContract.View view;

    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @Provides
    public MainContract.Presenter providePresenter(MainContract.View view, BaseContract.Repository repo, Scheduler scheduler) {
        return new MainPresenter(view, repo, scheduler);
    }


    @Provides
    public MainContract.View provideView() {
        return view;
    }

}
