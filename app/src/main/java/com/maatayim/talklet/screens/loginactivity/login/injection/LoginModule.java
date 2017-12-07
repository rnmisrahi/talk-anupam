package com.maatayim.talklet.screens.loginactivity.login.injection;

import android.content.Context;

import com.facebook.AccessToken;
import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.loginactivity.login.LoginContract;
import com.maatayim.talklet.screens.loginactivity.login.LoginPresenter;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by Sophie on 5/21/2017.
 */

@Module
public class LoginModule {

    private final LoginContract.View view;

    public LoginModule(LoginContract.View view) {
        this.view = view;
    }

    @Provides
    public LoginContract.Presenter providePresenter(LoginPresenter presenter) {
        return presenter;
    }

    @Provides
    public LoginContract.View provideView() {
        return view;
    }



}
