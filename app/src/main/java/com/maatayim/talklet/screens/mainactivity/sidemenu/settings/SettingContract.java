package com.maatayim.talklet.screens.mainactivity.sidemenu.settings;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;

import java.util.List;

/**
 * Created by Sophie on 6/18/2017.
 */

public interface SettingContract {

    interface Presenter extends BaseContract.Presenter{

        void getData();
    }

    interface View extends BaseContract.View{
        void onDataReceived(List<Child> children);

        void onChildrenLoadError();
    }
}
