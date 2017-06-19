package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv;

import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.WordsCount;

import java.util.Date;

/**
 * Created by Sophie on 6/11/2017.
 */

public class CalendarWordsObj {

    private final Date date;
    private final WordsCount wordsCount;
    private boolean isMiddle;

    public CalendarWordsObj(Date date, WordsCount wordsCount, boolean isMiddle){

        this.date = date;
        this.wordsCount = wordsCount;

        this.isMiddle = isMiddle;
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
}
