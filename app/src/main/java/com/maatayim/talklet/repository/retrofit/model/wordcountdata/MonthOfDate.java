package com.maatayim.talklet.repository.retrofit.model.wordcountdata;

import java.util.List;

/**
 * Created by Sophie on 5/28/2017.
 */

public class MonthOfDate {

    private List<Date> date;

    public MonthOfDate(List<Date> date){

        this.date = date;
    }

    public List<Date> getDate() {
        return date;
    }

    public void setDate(List<Date> date) {
        this.date = date;
    }
}
