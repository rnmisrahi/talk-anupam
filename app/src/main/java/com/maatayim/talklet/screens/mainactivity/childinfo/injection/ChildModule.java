package com.maatayim.talklet.screens.mainactivity.childinfo.injection;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.ChildContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.ChildPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by Sophie on 5/28/2017
 */

@Module
public class ChildModule {

    private final ChildContract.View view;

    public ChildModule(ChildContract.View view) {
        this.view = view;
    }

    @Provides
    public ChildContract.Presenter providePresenter(ChildContract.View view, BaseContract.Repository repo, Scheduler scheduler) {
        return new ChildPresenter(view, repo, scheduler);
    }


    @Provides
    public ChildContract.View provideView() {
        return view;
    }

}
