package com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.injection;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.ChildMainContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.ChildMainPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by Sophie on 5/28/2017
 */

@Module
public class ChildMainModule {

    private final ChildMainContract.View view;

    public ChildMainModule(ChildMainContract.View view) {
        this.view = view;
    }

    @Provides
    public ChildMainContract.Presenter providePresenter(ChildMainContract.View view, BaseContract.Repository repo, Scheduler scheduler) {
        return new ChildMainPresenter(view, repo, scheduler);
    }


    @Provides
    public ChildMainContract.View provideView() {
        return view;
    }

}
