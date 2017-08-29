package com.maatayim.talklet.screens.mainactivity.record;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.mainscreen.MainScreenChild;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 6/27/2017
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
        repo.getMainScreenChildrenList()
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableSingleObserver<List<MainScreenChild>>() {
                    @Override
                    public void onSuccess(@NonNull List<MainScreenChild> mainScreenChildren) {
                        view.onDataReceived(mainScreenChildren);

                        List<TipTicket> tickets = new ArrayList<TipTicket>();

                        for (MainScreenChild child : mainScreenChildren) {
                            for (MainScreenChild.Tip tip : child.getTips()) {
                                tickets.add(new TipTicket(tip.getText(), tip.getTipType(), child.getUrl()));
                            }
                        }

                        view.initViewpager(tickets, mainScreenChildren.size() > 1);


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onTipsLoadError();
                    }
                });


    }


    public void getAllChildrenTips() {
//        repo.getRecotdTipsList()
//                .subscribeOn(Schedulers.io())
//                .observeOn(scheduler)
//                .subscribeWith(new DisposableObserver<List<TipTicket>>() {
//                    @Override
//                    public void onNext(@NonNull List<TipTicket> TipTickets) {
//                        view.initViewpager(TipTickets);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        view.onDataLoadError();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }
}
