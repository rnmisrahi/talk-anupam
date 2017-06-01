package com.maatayim.talklet.screens.login;


import android.content.Context;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.baseline.events.AddLoginFragmentEvent;
import com.maatayim.talklet.screens.login.signup.SignupFragment;
import com.facebook.AccessToken;
import com.facebook.login.LoginResult;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 5/21/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private Context context;
    private BaseContract.Repository repository;

    @Inject
    public LoginPresenter(LoginContract.View view, Context context, BaseContract.Repository repository){
        this.view = view;
        this.context = context;
        this.repository = repository;
    }


    @Override
    public void connect() {
        //get Facebook information and stuff and logic
//        view.displayConnectionDialog();

        mockConnectToFacebook();
    }

    @Override
    public void saveToken(LoginResult loginResult) {
        repository.saveFacebookLoginToken(loginResult)
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

    @Override
    public void checkIfLoggedIn(final AccessToken currentAccessToken) {

        if (currentAccessToken != null){

            repository.getFacebookLoginToken()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<AccessToken>() {
                        @Override
                        public void onNext(@NonNull AccessToken oldToket) {
                            if(currentAccessToken.getToken().equals(oldToket)){
                                // already connected
                                view.setConnection();
                                EventBus.getDefault().post(new AddLoginFragmentEvent(new SignupFragment()));


                            }else{
//                                view.displayFacebookLoginInterface();
                            }
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

    private void mockConnectToFacebook() {
        view.displayFacebookLoginInterface();
    }
}
