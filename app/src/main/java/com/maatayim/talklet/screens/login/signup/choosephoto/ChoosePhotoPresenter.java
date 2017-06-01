package com.maatayim.talklet.screens.login.signup.choosephoto;

import android.net.Uri;

import com.maatayim.talklet.baseline.BaseContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 5/25/2017.
 */

public class ChoosePhotoPresenter implements ChoosePhotoContract.Presenter {

    private ChoosePhotoContract.View view;
    private BaseContract.Repository repository;

    public ChoosePhotoPresenter(ChoosePhotoContract.View view, BaseContract.Repository repository){

        this.view = view;
        this.repository = repository;
    }

    @Override
    public void saveImage(Uri photo) {
        //// TODO: 5/25/2017 save image
//        EventBus.getDefault().post();

        repository.saveBabysPhoto(photo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        //// TODO: 5/25/2017 Save data
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }
}
