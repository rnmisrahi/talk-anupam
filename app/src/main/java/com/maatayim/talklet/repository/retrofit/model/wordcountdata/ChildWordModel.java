package com.maatayim.talklet.repository.retrofit.model.wordcountdata;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by Sophie on 5/28/2017
 */

public class ChildWordModel {

    private List<DailyWords> days;

    public ChildWordModel(List<DailyWords> days){

        this.days = days;
    }

    public List<DailyWords> getDays() {
        return days;
    }

    public void setDays(List<DailyWords> date) {
        this.days = date;
    }
}
