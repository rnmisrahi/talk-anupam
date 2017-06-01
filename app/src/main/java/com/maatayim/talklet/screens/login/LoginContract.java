package com.maatayim.talklet.screens.login;


import com.maatayim.talklet.baseline.BaseContract;
import com.facebook.AccessToken;
import com.facebook.login.LoginResult;

import dagger.Component;

/**
 * Created by Sophie on 5/21/2017.
 */

@Component
public interface LoginContract {

    interface Presenter extends BaseContract.Presenter {

        void connect();

        void saveToken(LoginResult loginResult);

        void checkIfLoggedIn(AccessToken currentAccessToken);
    }

    interface View extends BaseContract.View {

        //region Optional
        void displayConnectionDialog();
        //endregion



        void displayFacebookLoginInterface();

//        void onConnectionFailed();

        void setConnection();
    }
}
