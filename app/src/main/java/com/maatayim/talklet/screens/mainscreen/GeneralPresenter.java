package com.maatayim.talklet.screens.mainscreen;

import android.support.v4.util.Pair;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainscreen.generalticket.GeneralTipTicket;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Sophie on 5/26/2017.
 */

public class GeneralPresenter implements GeneralContract.Presenter {

    private GeneralContract.View view;
    private BaseContract.Repository repository;

    @Inject
    public GeneralPresenter(GeneralContract.View view, BaseContract.Repository repository) {

        this.view = view;
        this.repository = repository;
    }


    @Override
    public void getData() {

        getChildrenList();

    }

    private void getScreenData(String id){
        getTips(id);
        getWordsCount(id);
    }


    private void getChildrenList(){

        repository.getChildrenList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Child>>() {
                    @Override
                    public void onNext(@NonNull List<Child> children) {
                        getScreenData(children.get(0).getId());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private void getWordsCount(String id) {
        repository.getWordsCount(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Pair<Integer, Integer>>() {
                    @Override
                    public void onNext(@NonNull Pair<Integer, Integer> wordsCount) {
                        view.updateWordsCount(wordsCount.first, wordsCount.second);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void getTips(String id) {
        repository.getTipsList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<GeneralTipTicket>>() {
            @Override
            public void onNext(@NonNull List<GeneralTipTicket> generalTipTickets) {
                view.updateTipsViewPager(generalTipTickets);
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
