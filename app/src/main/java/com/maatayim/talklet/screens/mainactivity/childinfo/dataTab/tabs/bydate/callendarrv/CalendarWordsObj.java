package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv;

import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.WordsCount;

import java.util.Date;

/**
 * Created by Sophie on 6/11/2017.
 */

public class CalendarWordsObj {

    private final Date date;
    private final WordsCount wordsCount;

    public CalendarWordsObj(Date date, WordsCount wordsCount){

        this.date = date;
        this.wordsCount = wordsCount;

    }

    public Date getDate() {
        return date;
    }

    public WordsCount getWordsCount() {
        return wordsCount;
    }
}
