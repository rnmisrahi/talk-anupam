package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.byrecording;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv.CalendarWordsObj;

import java.util.List;

/**
 * Created by Sophie on 6/20/2017
 */

public interface ByRecordingContract {

    interface Presenter extends BaseContract.Presenter{
        void getData(String id);
    }

    interface View extends BaseContract.View{
        void loadCalendarData(final List<CalendarWordsObj> calendarList);

        void errorOnLoadCalendarData();

    }




}
