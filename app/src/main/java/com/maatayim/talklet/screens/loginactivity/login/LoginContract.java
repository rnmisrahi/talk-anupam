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

        void checkIfLoggedIn();

        void checkIfSignedUp();

        void saveUserFBDetails(UserDetails userDetails);

        void sendUserFacebookData();
    }

    interface View extends BaseContract.View {


        void onInvalidToken();

        void setConnection();

        void displayFacebookLoginInterface();

        void onFacebookLoginSuccess();

        void onFacebookLoginFailed();

        void onAlreadySignedUpFailed();

        void onSignedUpSuccess();

        void onSaveUserFBDataSuccess();

        void onSaveUserFBDataFailed();

        void unlockLoginProcess();

        void alreadyLoggedIn();

        void receiveServerTokenSuccess(LoginResult loginResult);
    }
}
