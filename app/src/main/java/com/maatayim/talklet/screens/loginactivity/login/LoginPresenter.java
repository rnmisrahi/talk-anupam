package com.maatayim.talklet.screens.loginactivity.login;


import android.content.Context;
import android.util.Log;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.baseline.events.DownloadCompleteEvent;
import com.facebook.login.LoginResult;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Sophie on 5/21/2017
 */

public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = "LoginPresenter";

    private LoginContract.View view;

    private Context context;

    private BaseContract.Repository repository;

    private Scheduler scheduler;

    private EventBus eventBus;

    @Inject
    public LoginPresenter(LoginContract.View view,
                          Context context,
                          BaseContract.Repository repository,
                          Scheduler scheduler,
                          EventBus eventBus
    ) {
        this.view = view;
        this.context = context;
        this.repository = repository;
        this.scheduler = scheduler;
        this.eventBus = eventBus;
    }


    @Override
    public void saveAndSendFacebookId(LoginResult loginResult) {
        sendFacebookID(loginResult);


//        repository.saveFacebookLoginToken(loginResult, context)
//                  .subscribeOn(Schedulers.io())
//                  .observeOn(scheduler)
//                  .subscribe(new DisposableCompletableObserver() {
//                      @Override
//                      public void onComplete() {
//                          sendFacebookID(loginResult);
//                      }
//
//                      @Override
//                      public void onError(@NonNull Throwable e) {
//                          view.onFacebookLoginFailed();
//                      }
//                  });
    }


    private Completable signup() {
        return repository.checkIfSignedUp(context)
                         .observeOn(scheduler)
                         .doOnSuccess(aBoolean -> {
                             if (!aBoolean) {
                                 view.goToSignupScreen();
                             }
                         }).filter(aBoolean -> aBoolean)
                         .observeOn(scheduler)
                         .doOnSuccess(aBoolean -> view.navigateToMainActivity())
                         .doOnError(throwable -> {
                             Log.d(TAG, "signup: " + throwable.getMessage());
                             view.onInvalidToken();
                         })
                         .flatMapCompletable(aBoolean -> downloadData());

    }


    @Override
    public void sendFacebookID(LoginResult loginResult) {
        repository.sendFacebookID(loginResult)
                  .andThen(signup())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribeWith(new DisposableCompletableObserver() {
                      @Override
                      public void onComplete() {
                          view.finishLoginActivity();

                      }

                      @Override
                      public void onError(@NonNull Throwable e) {
                          if (e instanceof IllegalAccessException) {
                              view.displayFacebookError();
                          }
                          Log.e(TAG, "", e);
                      }
                  });
    }


    @Override
    public void checkIfLoggedIn() {
        repository.checkIfTokenFound(context)
                  .observeOn(scheduler)
                  .doOnSuccess(tokenFound -> {
                      if (!tokenFound) {
                          view.unlockLoginProcess();
                      }
                  })
                  .filter(tokenFound -> tokenFound)
                  .flatMap(aBoolean -> signup().toMaybe())

                  .observeOn(scheduler)
                  .subscribeWith(new DisposableMaybeObserver<Object>() {

                      @Override
                      public void onComplete() {
                          Log.d(TAG, "onComplete: ");
                      }

                      @Override
                      public void onSuccess(Object o) {
                          view.finishLoginActivity();
                      }

                      @Override
                      public void onError(@NonNull Throwable e) {
                          Log.d(TAG, "" + e);
                      }
                  });
    }


    @Override
    public void checkIfSignedUp() {


    }

    private Completable downloadData() {
        return repository.downloadKids()
                         .observeOn(AndroidSchedulers.mainThread())
                         .doOnComplete(() -> EventBus.getDefault()
                                                     .post(new DownloadCompleteEvent(true)))
                         .andThen(repository.downloadTips())
                         .observeOn(AndroidSchedulers.mainThread())
                         .doOnComplete(() -> EventBus.getDefault()
                                                     .post(new DownloadCompleteEvent(true)))
                         .andThen(repository.downloadCountData())
                         .observeOn(AndroidSchedulers.mainThread())
                         .doOnComplete(() -> EventBus.getDefault()
                                                     .post(new DownloadCompleteEvent(true)));
//                  .subscribeWith(new DisposableCompletableObserver() {
//                      @Override
//                      public void onComplete() {
//                          if (noChildren) {

//                          }

//                      }
//
//                      @Override
//                      public void onError(Throwable e) {
//                          Log.e(TAG, "onError: ", e);
//                      }
//                  });
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
                          Log.e(TAG, "onError: saveUserFBDetails ", e);
                          view.onSaveUserFBDataFailed();
                      }
                  });
    }


}
