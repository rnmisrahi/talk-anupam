package com.maatayim.talklet.screens.loginactivity.signup;

import android.net.Uri;

import com.maatayim.talklet.baseline.BaseContract;

import java.util.Date;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 5/22/2017
 */

public class SignUpPresenter implements SignupContract.Presenter {


    BaseContract.Repository repository;
    private Scheduler scheduler;

    private SignupContract.View view;


    @Inject
    public SignUpPresenter(SignupContract.View view, BaseContract.Repository repository, Scheduler scheduler) {
        this.view = view;
        this.repository = repository;
        this.scheduler = scheduler;
    }


//    public void checkIfBabysPhotoExists(){
//        io.reactivex.Observable<Uri> photo = null;
//
//        try{
//            photo = repository.getBaybsPhoto("1234L");
//            if (photo != null){
//                view.onWordsTypesDataReceived(photo);
//            }
//
//        }catch (Throwable e){
//
//        }
//
//
//    }


    public void saveSignUpDetails(String name, Date date, String babysPhoto) {

        repository.addChild(name, date, babysPhoto)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onDataSaveSuccess();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onDataSaveFailure();
                    }
                });


    }
}
