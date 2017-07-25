package com.maatayim.talklet.repository.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sophie on 7/16/2017
 */

public class RealmCountData extends RealmObject {

    @PrimaryKey
    private String id;
    private long date;
    private String childId;
    private int wordCount;
    private int expectedWordCount;

    public RealmCountData(String id, String childId, int wordCount, int expectedWordCount, long date) {
        this.id = id;
        this.childId = childId;
        this.wordCount = wordCount;
        this.expectedWordCount = expectedWordCount;
        this.date = date;
    }

    public RealmCountData() {
    }

    public RealmCountData(RealmCountData countData) {

        this.id = countData.getId();
        this.childId = countData.getChildId();
        this.wordCount = countData.getWordCount();
        this.expectedWordCount = countData.getExpectedWordCount();
        this.date = countData.getDate();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getExpectedWordCount() {
        return expectedWordCount;
    }

    public void setExpectedWordCount(int expectedWordCount) {
        this.expectedWordCount = expectedWordCount;
    }


    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public long getDate() {
        return date;
    }
}
