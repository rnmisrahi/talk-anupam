package com.maatayim.talklet.screens.mainactivity.childinfo.generaltab;


import com.maatayim.talklet.baseline.BaseContract;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 6/6/2017
 */

public class ChildMainPresenter implements ChildMainContract.Presenter {


    private final ChildMainContract.View view;
    private final BaseContract.Repository repository;
    private final Scheduler scheduler;

    @Inject
    public ChildMainPresenter(ChildMainContract.View view, BaseContract.Repository repository, Scheduler scheduler){

        this.view = view;
        this.repository = repository;
        this.scheduler = scheduler;
    }

    @Override
    public void getData(int id) {

        repository.downloadWordsOfTheDay(id)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        getChildTipsAndWords(id);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onDownloadError();
                    }
                });



    }


    private void getChildTipsAndWords(int id){
        repository.getChildTipsAndWords(id)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableSingleObserver<GeneralTabChildObj>() {
                    @Override
                    public void onSuccess(@NonNull GeneralTabChildObj generalTabChildObj) {
                        view.onDataReceived(generalTabChildObj);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onDataLoadError();
                    }
                });
    }


}
