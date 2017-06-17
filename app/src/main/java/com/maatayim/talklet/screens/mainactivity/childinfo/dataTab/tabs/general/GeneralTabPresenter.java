package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 6/7/2017.
 */

public class GeneralTabPresenter implements GeneralTabContract.Presenter {

    private final GeneralTabContract.View view;
    private final BaseContract.Repository repository;
    private final Scheduler scheduler;

    @Inject
    public GeneralTabPresenter(GeneralTabContract.View view, BaseContract.Repository repository,
                               Scheduler scheduler){

        this.view = view;
        this.repository = repository;
        this.scheduler = scheduler;
    }

    @Override
    public void getData(String id) {
        repository.getTotalWordsCount(id)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableObserver<WordsCount>() {
                    @Override
                    public void onNext(@NonNull WordsCount wordsCount) {
                        view.onDataReceived(wordsCount.getTotalWordsCount(),
                                wordsCount.getUniqueWords(),
                                wordsCount.getNewWords(),
                                wordsCount.getAdvanceWords());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.wordsCountLoadError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}