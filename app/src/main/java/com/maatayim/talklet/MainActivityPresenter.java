package com.maatayim.talklet;

import com.maatayim.talklet.baseline.BaseContract;

import javax.inject.Inject;

import io.reactivex.Scheduler;

/**
 * Created by Sophie on 7/5/2017
 */

public class MainActivityPresenter implements MainActivityContract.Presenter{


    private final MainActivityContract.View view;
    private final BaseContract.Repository repo;
    private final Scheduler scheduler;

    @Inject
    public MainActivityPresenter(MainActivityContract.View view, BaseContract.Repository repo,
                                 Scheduler scheduler){

        this.view = view;
        this.repo = repo;
        this.scheduler = scheduler;
    }



    public void downloadData(){
        repo.downloadKids();
        repo.downloadTips();

    }
}
