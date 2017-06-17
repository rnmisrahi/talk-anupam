package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;

/**
 * Created by Sophie on 6/6/2017.
 */

public interface DataTabContract {

    interface Presenter extends BaseContract.Presenter{

        void getData(String babyId);

    }

    interface View extends  BaseContract.View{
        void onDataReceived(Child child);

        void onBabyPhotoLoadError();
    }

}
