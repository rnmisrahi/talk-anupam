package com.maatayim.talklet.screens.mainactivity.childinfo.generaltab;

import android.util.Pair;

import com.maatayim.talklet.utils.Utils;

import java.util.Date;

/**
 * Created by Sophie on 6/6/2017
 */

public class RecordingObj {


    private String id;
    private int number;
    private Date date;
    private int wordCount;
    private int wordCountGoal;
    private long duration;
    private boolean isSelected;



    public RecordingObj(String id, int number, Date date, int wordCount,
                        int wordCountGoal, long duration, boolean isSelected){

        this.id = id;
        this.number = number;
        this.date = date;
        this.wordCount = wordCount;
        this.wordCountGoal = wordCountGoal;
        this.duration = duration;
        this.isSelected = isSelected;
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

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }


    public String getDurationStr(){
        return Utils.getDurationRecordStr(duration);
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getWordCountGoal() {
        return wordCountGoal;
    }

    public void setWordCountGoal(int wordCountGoal) {
        this.wordCountGoal = wordCountGoal;
    }
}
