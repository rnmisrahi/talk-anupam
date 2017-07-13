package com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou;


import android.widget.TextView;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.loginactivity.login.UserDetails;

import java.util.Date;

/**
 * Created by Sophie on 6/18/2017
 */

public interface AboutYouContract {

    interface Presenter extends BaseContract.Presenter{
        void getData();

        void setLanguageList(android.view.View layout, TextView view);

        void updateAboutYouData(AboutUserObj aboutUserObj);
    }

    interface View extends BaseContract.View{
        void setLanguageOnView(android.view.View layout, int statusID, TextView view);

        void loadUserDetails(AboutUserObj aboutUserObj);

        void onLoadUserDetailsFailure();

        void onUpdateDataSuccess();

    }
}
