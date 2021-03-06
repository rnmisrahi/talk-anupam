package com.maatayim.talklet.screens.mainactivity.childinfo;

import android.net.Uri;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.mainscreen.MainScreenChild;

/**
 * Created by Sophie on 6/6/2017.
 */

public interface ChildContract {

    interface Presenter extends BaseContract.Presenter{

        void getData(int babyId);

    }

    interface View extends  BaseContract.View{
        void onDataReceived(             Child child);

        void onbabyPhotoLoadError();
    }

}
