package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 6/6/2017.
 */

public class DataTabPresenter implements DataTabContract.Presenter {


    private final DataTabContract.View view;
    private final BaseContract.Repository repository;
    private Scheduler scheduler;

    @Inject
    public DataTabPresenter(DataTabContract.View view, BaseContract.Repository repository, Scheduler scheduler){
        this.view = view;
        this.repository = repository;

        this.scheduler = scheduler;
    }


    @Override
    public void getData(String babyId) {

        repository.getChild(babyId)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableSingleObserver<Child>() {
                    @Override
                    public void onSuccess(@NonNull Child child) {
                        view.onDataReceived(child);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onBabyPhotoLoadError();
                    }
                });


    }




}
