package com.maatayim.talklet.repository.retrofit.model.general;

import java.util.List;

/**
 * Created by Sophie on 5/28/2017
 */

public class ChildDataResponse {

    private List<Tip> tips;
    private List<Recording> recordings;
    private List<TodaysWord> todaysWords;

    public ChildDataResponse(List<Tip> tips, List<Recording> recordings, List<TodaysWord> todaysWords){

        this.tips = tips;
        this.recordings = recordings;
        this.todaysWords = todaysWords;
    }


    public List<Tip> getTips() {
        return tips;
    }

    public void setTips(List<Tip> tips) {
        this.tips = tips;
    }

    public List<Recording> getRecordings() {
        return recordings;
    }

    public void setRecordings(List<Recording> recordings) {
        this.recordings = recordings;
    }

    public List<TodaysWord> getTodaysWords() {
        return todaysWords;
    }

    public void setTodaysWords(List<TodaysWord> todaysWords) {
        this.todaysWords = todaysWords;
    }
}
