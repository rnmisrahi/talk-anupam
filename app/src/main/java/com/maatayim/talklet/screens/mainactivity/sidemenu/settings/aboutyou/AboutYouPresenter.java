package com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou;

import com.maatayim.talklet.baseline.BaseContract;

import javax.inject.Inject;

import io.reactivex.Scheduler;

/**
 * Created by Sophie on 6/18/2017.
 */

public class AboutYouPresenter implements AboutYouContract.Presenter {


    private final AboutYouContract.View view;
    private final BaseContract.Repository repo;
    private final Scheduler scheduler;

    @Inject
    public AboutYouPresenter(AboutYouContract.View view, BaseContract.Repository repo,
                             Scheduler scheduler){

        this.view = view;
        this.repo = repo;
        this.scheduler = scheduler;
    }
}
