package com.maatayim.talklet.repository.retrofit.model.general;

/**
 * Created by Sophie on 5/28/2017.
 */

public class Recording {

    private String id;
    private int number;
    private long date;
    private WordCount wordCount;
    private long duration;

    public Recording(String id, int number, long date, WordCount wordCount, long duration){

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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public WordCount getWordCount() {
        return wordCount;
    }

    public void setWordCount(WordCount wordCount) {
        this.wordCount = wordCount;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
