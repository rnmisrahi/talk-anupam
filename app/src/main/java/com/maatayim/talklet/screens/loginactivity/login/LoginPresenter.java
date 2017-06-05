package com.maatayim.talklet.screens.loginactivity.login;


import android.content.Context;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.loginactivity.login.injection.AccessTokenWrapper;
import com.facebook.login.LoginResult;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.Scheduler;
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
    public void saveToken(LoginResult loginResult) {
        repository.saveFacebookLoginToken(loginResult)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onFacebookLoginSuccess();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onFacebookLoginFailed();
                    }
                });
    }

    @Override
    public void checkIfLoggedIn() {
        if (!accessToken.isNull()) {
            repository.getFacebookLoginToken()
                    .subscribeOn(Schedulers.io())
                    .observeOn(scheduler)
                    .subscribeWith(new DisposableObserver<String>() {
                        @Override
                        public void onNext(@NonNull String oldToken) {
                            if (accessToken.getToken().equals(oldToken)) {
                                // already connected
                                view.setConnection();
                            } else {
                                view.onInvalidToken();
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
//                            Log.d(TAG, "onError: ");
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }else{
            view.onInvalidToken();
        }


    }


//    @Override
//    public void testConnect() {
//        mockConnectToFacebook();
//    }

    private void mockConnectToFacebook() {
        view.displayFacebookLoginInterface();
    }
}
