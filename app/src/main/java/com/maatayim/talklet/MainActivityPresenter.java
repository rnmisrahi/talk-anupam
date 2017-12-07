package com.maatayim.talklet;

import android.content.Context;
import android.util.Log;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.baseline.events.DownloadCompleteEvent;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Sophie on 7/5/2017
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {


    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private static final String TAG = "MainActivityPresenter";

    private final MainActivityContract.View view;

    private final BaseContract.Repository repo;

    private final Scheduler scheduler;

    private DisposableCompletableObserver disposable;

    @Inject
    public MainActivityPresenter(MainActivityContract.View view, BaseContract.Repository repo,
                                 Scheduler scheduler) {

        this.view = view;
        this.repo = repo;
        this.scheduler = scheduler;
    }


    public void downloadData() {
        compositeDisposable.add(repo.downloadKids()
                         .observeOn(AndroidSchedulers.mainThread())
                         .doOnComplete(() -> EventBus.getDefault()
                                                     .post(new DownloadCompleteEvent(true)))
                         .andThen(repo.downloadTips())
                         .observeOn(AndroidSchedulers.mainThread())
                         .doOnComplete(() -> EventBus.getDefault()
                                                     .post(new DownloadCompleteEvent(true)))
                         .andThen(repo.downloadCountData())
                         .observeOn(AndroidSchedulers.mainThread())
                         .doOnComplete(() -> EventBus.getDefault()
                                                     .post(new DownloadCompleteEvent(true)))
                         .subscribeWith(new DisposableCompletableObserver() {
                             @Override
                             public void onComplete() {
                                 Log.d(TAG, "onComplete: All Downloads Completed");
                             }

                             @Override
                             public void onError(Throwable e) {
                                 Log.e(TAG, "onError: ", e);
                             }
                         }));
    }


    public void logout(Context context) {
        repo.logout(context)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new DisposableCompletableObserver() {
                @Override
                public void onComplete() {
                    view.backToLogin();
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }
            });
    }


    public void detach() {
        compositeDisposable.dispose();
    }
}
