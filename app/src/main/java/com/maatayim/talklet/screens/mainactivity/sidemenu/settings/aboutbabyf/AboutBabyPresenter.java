package com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutbabyf;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou.AboutYouContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 6/27/2017.
 */

public class AboutBabyPresenter implements AboutBabyContract.Presenter {

    private final AboutBabyContract.View view;
    private final BaseContract.Repository repo;
    private final Scheduler scheduler;

    @Inject
    public AboutBabyPresenter(AboutBabyContract.View view, BaseContract.Repository repo,
                              Scheduler scheduler){

        this.view = view;
        this.repo = repo;
        this.scheduler = scheduler;
    }


    public void getData(String id){
        repo.getChild(id)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableObserver<Child>() {
                    @Override
                    public void onNext(@NonNull Child child) {
                        view.onDataReceived(child);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onChildLoadError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}