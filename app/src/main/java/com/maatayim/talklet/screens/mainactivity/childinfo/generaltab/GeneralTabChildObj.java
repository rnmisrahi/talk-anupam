package com.maatayim.talklet.screens.mainactivity.childinfo.generaltab;

import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;

import java.util.List;

/**
 * Created by Sophie on 7/6/2017
 */

public class GeneralTabChildObj {


    private String childName;
    private String childPhoto;
    private List<TipTicket> tips;
    private List<DaysWordsObj> wordsOfTheDay;
    private String topic;

    public GeneralTabChildObj(String childName, List<TipTicket> tips, List<DaysWordsObj> wordsOfTheDay, String topic, String childPhoto){

        this.childName = childName;
        this.tips = tips;
        this.wordsOfTheDay = wordsOfTheDay;
        this.topic = topic;
        this.childPhoto = childPhoto;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public void setTips(List<TipTicket> tips) {
        this.tips = tips;
    }

    public void setWordsOfTheDay(List<DaysWordsObj> wordsOfTheDay) {
        this.wordsOfTheDay = wordsOfTheDay;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getChildName() {
        return childName;
    }

    public List<TipTicket> getTips() {
        return tips;
    }

    public List<DaysWordsObj> getWordsOfTheDay() {
        return wordsOfTheDay;
    }

    public String getTopic() {
        return topic;
    }


    public String getChildPhoto() {
        return childPhoto;
    }
}
