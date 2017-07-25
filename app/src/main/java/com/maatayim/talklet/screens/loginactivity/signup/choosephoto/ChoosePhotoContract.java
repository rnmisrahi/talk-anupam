package com.maatayim.talklet.screens.loginactivity.signup.choosephoto;

import android.net.Uri;

import com.maatayim.talklet.baseline.BaseContract;

import dagger.Component;

/**
 * Created by Sophie on 5/25/2017
 */

@Component
public interface ChoosePhotoContract {
    interface Presenter extends BaseContract.Presenter{
        void saveImage(String photo);
    }

    interface View extends BaseContract.View {

    }
}
