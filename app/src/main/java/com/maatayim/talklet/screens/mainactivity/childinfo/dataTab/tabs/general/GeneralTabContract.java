package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general;

import android.support.v4.util.Pair;

import com.maatayim.talklet.baseline.BaseContract;

/**
 * Created by Sophie on 6/7/2017.
 */

public interface GeneralTabContract {

    interface Presenter extends BaseContract.Presenter{
        void getData(String id);
    }

    interface View extends  BaseContract.View{
        void onDataReceived(int wordsSaidCount, int totalGoalWords);

        void wordsCountLoadError();
    }
}
