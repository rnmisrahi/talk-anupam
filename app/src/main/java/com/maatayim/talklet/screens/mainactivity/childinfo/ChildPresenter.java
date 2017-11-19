package com.maatayim.talklet.screens.mainactivity.childinfo;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.mainscreen.MainScreenChild;

import javax.inject.Inject;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 6/6/2017.
 */

public class ChildPresenter implements ChildContract.Presenter {


    private final ChildContract.View view;
    private final BaseContract.Repository repository;
    private Scheduler scheduler;

    @Inject
    public ChildPresenter(ChildContract.View view, BaseContract.Repository repository, Scheduler scheduler){
        this.view = view;
        this.repository = repository;
        this.scheduler = scheduler;
    }


    @Override
    public void getData(int babyId) {

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
                        view.onbabyPhotoLoadError();
                    }
                });

    }




}
