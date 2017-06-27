package com.maatayim.talklet.screens.mainactivity.record;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 6/27/2017.
 */

public class RecordPresenter implements RecordContract.Presenter {

    private final RecordContract.View view;
    private final BaseContract.Repository repo;
    private final Scheduler scheduler;

    @Inject
    public RecordPresenter(RecordContract.View view, BaseContract.Repository repo,
                           Scheduler scheduler){

        this.view = view;
        this.repo = repo;
        this.scheduler = scheduler;
    }

    @Override
    public void getData() {
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

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
