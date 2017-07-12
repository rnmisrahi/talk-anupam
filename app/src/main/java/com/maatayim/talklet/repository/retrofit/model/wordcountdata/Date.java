package com.maatayim.talklet.repository.retrofit.model.wordcountdata;

import com.maatayim.talklet.repository.retrofit.model.general.Recording;
import com.maatayim.talklet.repository.retrofit.model.general.WordCount;

import java.util.List;

/**
 * Created by Sophie on 5/28/2017
 */

public class Date {

    private long date;
    private WordCount wordCount;
    private WordCount expectedWordCount;
    private List<Recording> recordings;

    public Date(long date, WordCount wordCount, WordCount expectedWordCount, List<Recording> recordings){

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

    public WordCount getWordCount() {
        return wordCount;
    }

    public void setWordCount(WordCount wordCount) {
        this.wordCount = wordCount;
    }

    public WordCount getExpectedWordCount() {
        return expectedWordCount;
    }

    public void setExpectedWordCount(WordCount expectedWordCount) {
        this.expectedWordCount = expectedWordCount;
    }

    public List<Recording> getRecordings() {
        return recordings;
    }

    public void setRecordings(List<Recording> recordings) {
        this.recordings = recordings;
    }
}
