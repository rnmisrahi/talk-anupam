package com.maatayim.talklet.repository.retrofit.model.wordcountdata;

import com.maatayim.talklet.repository.retrofit.model.general.WordCount;

import java.util.List;

/**
 * Created by Sophie on 5/28/2017
 */

public class WordCountResponse {

    private WordCount wordCount;
    private WordCount expectedWordCount;
    private List<MonthOfDate> dates;

    public WordCountResponse(WordCount wordCount, WordCount expectedWordCount, List<MonthOfDate> dates){

        this.wordCount = wordCount;
        this.expectedWordCount = expectedWordCount;
        this.dates = dates;
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

    public List<MonthOfDate> getDates() {
        return dates;
    }

    public void setDates(List<MonthOfDate> dates) {
        this.dates = dates;
    }
}
