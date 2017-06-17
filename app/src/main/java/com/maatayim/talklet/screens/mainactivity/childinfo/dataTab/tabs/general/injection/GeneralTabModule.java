package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.injection;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.GeneralTabContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.GeneralTabPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class GeneralTabModule {

    private final GeneralTabContract.View view;

    public GeneralTabModule(GeneralTabContract.View view) {
        this.view = view;
    }

    @Provides
    public GeneralTabContract.Presenter providePresenter(GeneralTabContract.View view, BaseContract.Repository repo, Scheduler scheduler){
        return new GeneralTabPresenter(view, repo, scheduler);
    }


    @Provides
    public GeneralTabContract.View provideView(){
        return view;
    }

}