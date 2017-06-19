package com.maatayim.talklet.screens.mainactivity.sidemenu.settings;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 6/18/2017.
 */

public class SettingPresenter implements SettingContract.Presenter {


    private final SettingContract.View view;
    private final BaseContract.Repository repo;
    private final Scheduler scheduler;

    @Inject
    public SettingPresenter(SettingContract.View view, BaseContract.Repository repo, Scheduler scheduler){

        this.view = view;
        this.repo = repo;
        this.scheduler = scheduler;
    }


    @Override
    public void getData() {
        getChildrenList();
    }



    private void getChildrenList() {
        repo.getChildrenList()
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableObserver<List<Child>>() {
                    @Override
                    public void onNext(@NonNull List<Child> children) {
                        view.onDataReceived(children);

                    }


                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onChildrenLoadError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
