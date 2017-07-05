package com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutbabyrv.aboutbabyf;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;

/**
 * Created by Sophie on 6/29/2017.
 */

public interface AboutBabyContract {

    interface View extends BaseContract.View{
        void onDataReceived(Child child);

        void onChildLoadError();
    }

    interface Presenter extends BaseContract.Presenter{

        void getData(String babyId);
    }
}
