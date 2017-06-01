package com.maatayim.talklet.screens.login.signup;

import android.net.Uri;

import com.maatayim.talklet.baseline.BaseContract;
import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 5/22/2017.
 */

public class SignUpPresenter implements SignupContract.Presenter {


    BaseContract.Repository repository;

    private SignupContract.View view;


    @Inject
    public SignUpPresenter(SignupContract.View view, BaseContract.Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void checkIfBabysPhotoExists(){
        io.reactivex.Observable<Uri> photo = null;

        try{
            photo = repository.getBaybsPhoto(1234L);
            if (photo != null){
                view.onDataReceived(photo);
            }

        }catch (Throwable e){

        }


    }


    public void saveSignUpDetails(String name, Calendar date) {

        if (date == null) {
            view.displayNoBirthdayError();
            return;
        }

        if (name.isEmpty()) {
            view.displayNoBirthdayError();
            return;
        }


        repository.saveSignupDetails(name, date.getTime())
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
