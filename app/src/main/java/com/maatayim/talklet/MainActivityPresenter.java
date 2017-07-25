package com.maatayim.talklet;

import android.content.Context;
import android.util.Log;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.baseline.events.DowmloadCompleteEvent;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.facebook.login.widget.ProfilePictureView.TAG;

/**
 * Created by Sophie on 7/5/2017
 */

public class MainActivityPresenter implements MainActivityContract.Presenter{


    private final MainActivityContract.View view;
    private final BaseContract.Repository repo;
    private final Scheduler scheduler;

    @Inject
    public MainActivityPresenter(MainActivityContract.View view, BaseContract.Repository repo,
                                 Scheduler scheduler){

        this.view = view;
        this.repo = repo;
        this.scheduler = scheduler;
    }



    public void downloadData(){
        repo.downloadKids()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        EventBus.getDefault().post(new DowmloadCompleteEvent(true));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: downloadKids failed");
                    }
                });


        repo.downloadTips()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        EventBus.getDefault().post(new DowmloadCompleteEvent(true));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: downloadTips failed");
                    }
                });

        repo.downloadCountData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        EventBus.getDefault().post(new DowmloadCompleteEvent(true));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: downloadCountData failed");
                    }
                });


//        repo.downloadWordsOfTheDay()
//        repo.downloadWordsCount();
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
}
