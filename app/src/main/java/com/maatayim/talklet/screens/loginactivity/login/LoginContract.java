package com.maatayim.talklet.screens.loginactivity.login;


import com.maatayim.talklet.baseline.BaseContract;
import com.facebook.login.LoginResult;

import dagger.Component;

/**
 * Created by Sophie on 5/21/2017.
 */

@Component
public interface LoginContract {

    interface Presenter extends BaseContract.Presenter {

//        void testConnect();

        void saveToken(LoginResult loginResult);

        void checkIfLoggedIn();
    }

    interface View extends BaseContract.View {


        void onInvalidToken();

        void setConnection();

        void displayFacebookLoginInterface();

        void onFacebookLoginSuccess();

        void onFacebookLoginFailed();
    }
}
