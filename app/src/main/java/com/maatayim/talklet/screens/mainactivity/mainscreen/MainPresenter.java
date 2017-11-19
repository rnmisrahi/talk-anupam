package com.maatayim.talklet.screens.mainactivity.mainscreen;

import android.support.v4.util.Pair;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Sophie on 5/26/2017
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private BaseContract.Repository repository;
    private Scheduler scheduler;

    @Inject
    public MainPresenter(MainContract.View view, BaseContract.Repository repository, Scheduler scheduler) {
        this.view = view;
        this.repository = repository;
        this.scheduler = scheduler;
    }


    @Override
    public void getData() {
        getChildrenList();

    }


    private void getChildrenList() {
        repository.getMainScreenChildrenList()
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableSingleObserver<List<MainScreenChild>>() {
                    @Override
                    public void onSuccess(@NonNull List<MainScreenChild> children) {

                        List<TipTicket> tickets = new ArrayList<TipTicket>();

                        for (MainScreenChild child : children) {
                            for (MainScreenChild.Tip tip : child.getTips()) {
                                tickets.add(new TipTicket(tip.getText(), tip.getTipType(), child.getUrl()));
                            }
                        }
                        if (children.size() == 1) {
                            MainScreenChild mainScreenChild = children.get(0);
                            view.updateWordsCount(mainScreenChild.getWordCount(), mainScreenChild.getTotal());
                            view.setChildrenRecyclerView(children, false);
                        }else{
//                            view.updateWordsCountManyChildren();
                            view.setChildrenRecyclerView(children, true);
                        }

                        view.updateTipsViewPager(tickets, children.size() > 1);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onDisplayChildrenError();

                    }
                });

    }


    private void setScreenData(List<Child> children) {
        getAllChildrenTips();
//        view.setChildrenView();
    }


    private void setScreenData(Child child) {
        getTips(child.getId());
        getWordsCount(child.getId());
    }


    private void getTips(final int id) {
        repository.getTipsList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableObserver<List<TipTicket>>() {
                    @Override
                    public void onNext(@NonNull List<TipTicket> generalTipTickets) {
                        view.updateTipsViewPager(generalTipTickets, false);
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


    private void getWordsCount(int id) {
        repository.getWordsCount(id)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableObserver<Pair<Integer, Integer>>() {
                    @Override
                    public void onNext(@NonNull Pair<Integer, Integer> wordsCount) {
                        view.updateWordsCount(wordsCount.first, wordsCount.second);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onWordsCountLoadError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void getAllChildrenTips() {
        repository.getAllChildrenTips()
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableObserver<List<TipTicket>>() {
                    @Override
                    public void onNext(@NonNull List<TipTicket> generalTipTickets) {
                        view.updateTipsViewPager(generalTipTickets, true);
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
