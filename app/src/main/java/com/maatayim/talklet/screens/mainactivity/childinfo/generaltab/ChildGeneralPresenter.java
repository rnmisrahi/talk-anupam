package com.maatayim.talklet.screens.mainactivity.childinfo.generaltab;


import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.GeneralTipTicket;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 6/6/2017.
 */

public class ChildGeneralPresenter implements ChildGeneralContract.Presenter {


    private final ChildGeneralContract.View view;
    private final BaseContract.Repository repository;
    private final Scheduler scheduler;

    @Inject
    public ChildGeneralPresenter(ChildGeneralContract.View view, BaseContract.Repository repository, Scheduler scheduler){

        this.view = view;
        this.repository = repository;
        this.scheduler = scheduler;
    }

    @Override
    public void getData(String id) {
        getTips(id);
        getRecordings(id);
    }


    private void getTips(final String id) {
        repository.getTipsList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableObserver<List<GeneralTipTicket>>() {
                    @Override
                    public void onNext(@NonNull List<GeneralTipTicket> generalTipTickets) {
                        view.updateTipsViewPager(generalTipTickets);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onTipsLoadError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getRecordings(final String id) {
        repository.getRecordings(id)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableObserver<List<RecordingObj>>() {
                    @Override
                    public void onNext(@NonNull List<RecordingObj> recordings) {
                        view.initRecordingsRecyclerView(recordings);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onTipsLoadError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}