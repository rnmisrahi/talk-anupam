package com.maatayim.talklet.screens.mainactivity.childinfo.generaltab;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;

import java.util.List;

/**
 * Created by Sophie on 6/6/2017
 */

public interface ChildMainContract {

    interface Presenter extends BaseContract.Presenter{
        void getData(int id);
    }

    interface View extends BaseContract.View{
        void onDataReceived(GeneralTabChildObj generalTabChildObj);

        void updateTipsViewPager(List<TipTicket> ticketList);

        void onDataLoadError();
        void initRecordingsRecyclerView(List<RecordingObj> recordings);

        void onDownloadError();
    }
}
