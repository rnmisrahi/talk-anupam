package com.maatayim.talklet.repository.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sophie on 7/31/2017
 */

public class RealmRecording extends RealmObject{

    @PrimaryKey
    private int id;

    private int number;
    private long date;
    private int wordCounter;
    private int wordCounterGoal;
    private String duration;

    public RealmRecording() {
    }

    public RealmRecording(int id, int number, long date, int wordCounter, int wordCounterGoal, String duration) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.wordCounter = wordCounter;
        this.wordCounterGoal = wordCounterGoal;
        this.duration = duration;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getWordCounter() {
        return wordCounter;
    }

    public void setWordCounter(int wordCounter) {
        this.wordCounter = wordCounter;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getWordCounterGoal() {
        return wordCounterGoal;
    }

    public void setWordCounterGoal(int wordCounterGoal) {
        this.wordCounterGoal = wordCounterGoal;
    }
}
