package com.maatayim.talklet.screens.login.dagger;

import android.content.Context;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.login.LoginContract;
import com.maatayim.talklet.screens.login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

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
    public LoginContract.Presenter providePresenter(LoginContract.View view, Context context, BaseContract.Repository repo) {
        return new LoginPresenter(view, context, repo);
    }


    @Provides
    public LoginContract.View provideView() {
        return view;
    }
}
