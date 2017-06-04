package com.maatayim.talklet.screens.loginactivity.signup.dagger;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.loginactivity.signup.SignUpPresenter;
import com.maatayim.talklet.screens.loginactivity.signup.SignupContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sophie on 5/21/2017.
 */

@Module
public class SignupModule {

    private final SignupContract.View view;

    public SignupModule(SignupContract.View view) {
        this.view = view;
    }

    @Provides
    public SignupContract.Presenter providePresenter(SignupContract.View view, BaseContract.Repository repo) {
        return new SignUpPresenter(view, repo);
    }


    @Provides
    public SignupContract.View provideView() {
        return view;
    }
}
