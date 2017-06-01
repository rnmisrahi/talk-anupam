package com.maatayim.talklet.screens.login.signup;

import android.net.Uri;

import com.maatayim.talklet.baseline.BaseContract;

import dagger.Component;

/**
 * Created by Sophie on 5/24/2017.
 */

@Component
public interface SignupContract {

    interface Presenter extends BaseContract.Presenter{

    }



    interface View extends BaseContract.View {

        void onDataReceived(io.reactivex.Observable<Uri> uri);

        void displayNoBirthdayError();
    }
}
