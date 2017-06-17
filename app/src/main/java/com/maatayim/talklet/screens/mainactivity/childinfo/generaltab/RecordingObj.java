package com.maatayim.talklet.screens.mainactivity.childinfo.generaltab;

import android.util.Pair;

import java.util.Date;

/**
 * Created by Sophie on 6/6/2017.
 */

public class RecordingObj {


    private String id;
    private int number;
    private Date date;
    private Pair<Integer, Integer> wordCount;
    private long duration;

    public RecordingObj(String id, int number, Date date, Pair<Integer, Integer> wordCount, long duration){

        this.id = id;
        this.number = number;
        this.date = date;
        this.wordCount = wordCount;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Pair<Integer, Integer> getWordCount() {
        return wordCount;
    }

    public void setWordCount(Pair<Integer, Integer> wordCount) {
        this.wordCount = wordCount;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
