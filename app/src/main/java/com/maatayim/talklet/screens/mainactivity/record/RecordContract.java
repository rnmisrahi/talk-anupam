package com.maatayim.talklet.screens.mainactivity.record;

import android.content.Context;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.mainscreen.MainScreenChild;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;

import java.util.List;


/**
 * Created by Sophie on 6/27/2017
 */

public interface RecordContract {

    interface View extends BaseContract.View{

        void onDataReceived(List<MainScreenChild> mainScreenChildren);
        void initViewpager(List<TipTicket> tipsTickets, boolean isMoreThanOnChild);

        void onTipsLoadError();

        Context getViewContext();
    }

    interface Presenter extends BaseContract.Presenter{

        void updateChildren(List<ChildRecObj> childRecObjs);

        void getData();

        void startRecording();

    }
}
