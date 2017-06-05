package com.maatayim.talklet.screens.mainscreen.dagger;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainscreen.GeneralContract;
import com.maatayim.talklet.screens.mainscreen.GeneralPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by Sophie on 5/28/2017
 */

@Module
public class GeneralModule {

    private final GeneralContract.View view;

    public GeneralModule(GeneralContract.View view) {
        this.view = view;
    }

    @Provides
    public GeneralContract.Presenter providePresenter(GeneralContract.View view, BaseContract.Repository repo, Scheduler scheduler) {
        return new GeneralPresenter(view, repo, scheduler);
    }


    @Provides
    public GeneralContract.View provideView() {
        return view;
    }

}
