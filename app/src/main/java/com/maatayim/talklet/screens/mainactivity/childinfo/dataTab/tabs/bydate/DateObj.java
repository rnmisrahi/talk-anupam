package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate;

/**
 * Created by Sophie on 7/23/2017
 */

public class DateObj {


    private final String childId;
    private int wordCount;
    private int totalWords;
    private long date;
    private boolean isMiddle;

    public DateObj(String childId, int wordCount, int totalWords, long date, boolean isMiddle){

        this.childId = childId;
        this.wordCount = wordCount;
        this.totalWords = totalWords;
        this.date = date;
        this.isMiddle = isMiddle;
    }


    public String getChildId() {
        return childId;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getTotalWords() {
        return totalWords;
    }

    public long getDate() {
        return date;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public void setTotalWords(int totalWords) {
        this.totalWords = totalWords;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
