package com.maatayim.talklet.repository.retrofit.model.general;

import io.realm.annotations.PrimaryKey;

/**
 * Created by Sophie on 5/28/2017
 */

public class Recording {

    private int id;
    private int number;
    private String date;
    private int wordCounter;
    private String duration;

    public Recording(int id, int number, String date, int wordCounter, String duration){

        this.id = id;
        this.number = number;
        this.date = date;
        this.wordCounter = wordCounter;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWordCount() {
        return wordCounter;
    }

    public void setWordCount(int wordCounter) {
        this.wordCounter = wordCounter;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
