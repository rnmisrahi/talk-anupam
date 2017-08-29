package com.maatayim.talklet.screens.loginactivity.login;


import android.content.Context;
import android.util.Log;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.repository.retrofit.model.user.LoginResponse;
import com.maatayim.talklet.screens.loginactivity.login.injection.AccessTokenWrapper;
import com.facebook.login.LoginResult;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Sophie on 5/21/2017
 */

public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = "LoginPresenter";
    private LoginContract.View view;
    private Context context;
    private BaseContract.Repository repository;
    private AccessTokenWrapper accessToken;
    private Scheduler scheduler;
    private EventBus eventBus;

    @Inject
    public LoginPresenter(LoginContract.View view,
                          Context context,
                          BaseContract.Repository repository,
                          AccessTokenWrapper accessToken,
                          Scheduler scheduler,
                          EventBus eventBus
    ) {
        this.view = view;
        this.context = context;
        this.repository = repository;
        this.accessToken = accessToken;
        this.scheduler = scheduler;
        this.eventBus = eventBus;
    }


    @Override
    public void saveAndSendFacebookId(LoginResult loginResult) {
        repository.saveFacebookLoginToken(loginResult, context)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        sendFacebookID(loginResult);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onFacebookLoginFailed();
                    }
                });
    }

    private void sendFacebookID(LoginResult loginResult) {
        repository.login(context)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableSingleObserver<LoginResponse>() {
                    @Override
                    public void onSuccess(@NonNull LoginResponse loginResponse) {
                        if (loginResponse.getToken() == null){
                            view.unlockLoginProcess();
                        }else{
                            view.receiveServerTokenSuccess(loginResult);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: ",e );
                    }
                });
    }


    @Override
    public void checkIfLoggedIn() {
        repository.login(context)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableSingleObserver<LoginResponse>() {
                    @Override
                    public void onSuccess(@NonNull LoginResponse loginResponse) {
                        if (loginResponse.getToken() == null){
                            view.unlockLoginProcess();
                        }else{
                            view.alreadyLoggedIn();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: ",e );
                    }
                });
    }

    @Override
    public void checkIfSignedUp() {
        repository.login(context)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableSingleObserver<LoginResponse>() {
                    @Override
                    public void onSuccess(@NonNull LoginResponse loginResponse) {
                        if (loginResponse.isSignedUp()) {
                            view.onSignedUpSuccess();
                        } else {
                            view.onAlreadySignedUpFailed();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: ", e );
                    }
                });

    }

    @Override
    public void saveUserFBDetails(UserDetails userDetails) {

        repository.saveUserFBDetails(userDetails)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onSaveUserFBDataSuccess();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onSaveUserFBDataFailed();
                    }
                });
    }

    @Override
    public void sendUserFacebookData() {
//        repository.sendUserFacebookData()
//                .
    }


//    @Override
//    public void testConnect() {
//        mockConnectToFacebook();
//    }

    private void mockConnectToFacebook() {
        view.displayFacebookLoginInterface();
    }
}
