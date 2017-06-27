package com.maatayim.talklet.screens.mainactivity.mainscreen;

import android.support.v4.util.Pair;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
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


    private void getChildrenList(){
        repository.getChildrenList()
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableObserver<List<Child>>() {
                    @Override
                    public void onNext(@NonNull List<Child> children) {
                        view.setChildrenRecyclerView(children);
                        loadLastConnectionChild();
                    }


                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onDisplayChildrenError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void loadLastConnectionChild(){
        repository.getLastConnectionChild()
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableObserver<Child>() {
                    @Override
                    public void onNext(@NonNull Child lastConnectionChild) {
                        getScreenData(lastConnectionChild);
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

    private void getScreenData(Child child){
        getTips(child.getId());
        getWordsCount(child.getId());
    }


    private void getTips(final String id) {
        repository.getTipsList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableObserver<List<TipTicket>>() {
            @Override
            public void onNext(@NonNull List<TipTicket> generalTipTickets) {
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


    private void getWordsCount(String id) {
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



}
