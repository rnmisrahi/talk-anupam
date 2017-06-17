package com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.injection;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.ChildGeneralContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.ChildGeneralPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by Sophie on 5/28/2017
 */

@Module
public class ChildGeneralModule {

    private final ChildGeneralContract.View view;

    public ChildGeneralModule(ChildGeneralContract.View view) {
        this.view = view;
    }

    @Provides
    public ChildGeneralContract.Presenter providePresenter(ChildGeneralContract.View view, BaseContract.Repository repo, Scheduler scheduler) {
        return new ChildGeneralPresenter(view, repo, scheduler);
    }


    @Provides
    public ChildGeneralContract.View provideView() {
        return view;
    }

}
