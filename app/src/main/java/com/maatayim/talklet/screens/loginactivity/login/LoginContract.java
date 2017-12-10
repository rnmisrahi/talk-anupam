package com.maatayim.talklet.screens.loginactivity.login;


import android.content.Context;
import android.os.Bundle;

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

        void saveAndSendFacebookId(LoginResult loginResult);

        void sendFacebookID(LoginResult loginResult);

        void checkIfLoggedIn();

        void checkIfSignedUp();

        void saveUserFBDetails(UserDetails userDetails);

    }

    interface View extends BaseContract.View {


        void onInvalidToken();

        void setConnection();

        void displayFacebookLoginInterface();

        void onFacebookLoginSuccess();

        void onFacebookLoginFailed();

        void goToSignupScreen();

        void navigateToMainActivity();

        void onSaveUserFBDataSuccess();

        void onSaveUserFBDataFailed();

        void unlockLoginProcess();

        void alreadyLoggedIn();

        void receiveServerTokenSuccess(LoginResult loginResult);

        void finish();

        void finishLoginActivity();

        void displayFacebookError();

    }
}
