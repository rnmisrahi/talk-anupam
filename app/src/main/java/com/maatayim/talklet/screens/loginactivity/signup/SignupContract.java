package com.maatayim.talklet.screens.loginactivity.signup;

import android.net.Uri;

import com.maatayim.talklet.baseline.BaseContract;

import java.util.Calendar;
import java.util.Date;

import dagger.Component;

/**
 * Created by Sophie on 5/24/2017.
 */

@Component
public interface SignupContract {

    interface Presenter extends BaseContract.Presenter{

        void saveSignUpDetails(String name, Date date);

    }



    interface View extends BaseContract.View {

        void onDataReceived(io.reactivex.Observable<Uri> uri);

        void onDataSaveSuccess();

        void onDataSaveFailure();
    }
}
