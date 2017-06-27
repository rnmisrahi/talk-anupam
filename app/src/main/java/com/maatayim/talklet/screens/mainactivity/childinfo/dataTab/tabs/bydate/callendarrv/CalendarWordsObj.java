package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv;

import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.WordsCount;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.RecordingObj;

import java.util.Date;
import java.util.List;

/**
 * Created by Sophie on 6/11/2017.
 */

public class CalendarWordsObj {

    private final Date date;
    private final WordsCount wordsCount;
    private boolean isMiddle;
    private List<RecordingObj> recordsList;

    public CalendarWordsObj(Date date, WordsCount wordsCount, boolean isMiddle, List<RecordingObj> recordsList){

        this.date = date;
        this.wordsCount = wordsCount;
        this.isMiddle = isMiddle;
        this.recordsList = recordsList;

    }

    public Date getDate() {
        return date;
    }

    public WordsCount getWordsCount() {
        return wordsCount;
    }


    public boolean isMiddle() {
        return isMiddle;
    }

    public void setMiddle(boolean middle) {
        isMiddle = middle;
    }

    public List<RecordingObj> getRecordsList() {
        return recordsList;
    }
}
