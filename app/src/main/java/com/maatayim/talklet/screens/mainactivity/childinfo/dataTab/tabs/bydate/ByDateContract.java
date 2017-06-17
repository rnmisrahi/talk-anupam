package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate;

import android.support.v4.util.Pair;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv.CalendarWordsObj;

import java.util.List;

/**
 * Created by Sophie on 6/11/2017.
 */

public interface ByDateContract {

    interface Presenter extends BaseContract.Presenter{
        void getData(String id);
    }

    interface View extends BaseContract.View{
        void onWordsTypesDataReceived(Pair<Integer, Integer> totalWordsCount,
                                      Pair<Integer, Integer> uniqueWordsCount,
                                      Pair<Integer, Integer> newWordsCount,
                                      Pair<Integer, Integer> advanceWordsCount);

        void loadCalendarData(List<CalendarWordsObj> calendarList);

        void wordsCountLoadError();

        void errorOnLoadCalendarData();

        void onComplete();
    }
}
