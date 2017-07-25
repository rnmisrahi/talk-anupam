package com.maatayim.talklet.repository.retrofit.model.wordcountdata;

import com.maatayim.talklet.repository.retrofit.model.general.Recording;
import com.maatayim.talklet.repository.retrofit.model.general.WordCount;

import java.util.List;

/**
 * Created by Sophie on 5/28/2017
 */

public class DailyWords {

    private String id;
    private long date;
    private int wordCount;
    private int expectedWordCount;
    private List<Recording> recordings;
    private String childId;

    public DailyWords(String id, long date, int wordCount, int expectedWordCount, List<Recording> recordings) {
        this.id = id;
        this.date = date;
        this.wordCount = wordCount;
        this.expectedWordCount = expectedWordCount;
        this.recordings = recordings;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
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

    public List<Recording> getRecordings() {
        return recordings;
    }

    public void setRecordings(List<Recording> recordings) {
        this.recordings = recordings;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getId() {
        return id;
    }
}
