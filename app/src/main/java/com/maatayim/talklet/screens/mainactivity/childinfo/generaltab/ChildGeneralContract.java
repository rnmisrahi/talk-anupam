package com.maatayim.talklet.screens.mainactivity.childinfo.generaltab;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.GeneralTipTicket;

import java.util.List;

/**
 * Created by Sophie on 6/6/2017.
 */

public interface ChildGeneralContract {

    interface Presenter extends BaseContract.Presenter{
        void getData(String id);
    }

    interface View extends BaseContract.View{
        void onDataReceived();

        void updateTipsViewPager(List<GeneralTipTicket> ticketList);

        void onTipsLoadError();
        void initRecordingsRecyclerView(List<RecordingObj> recordings);
    }
}
