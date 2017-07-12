package com.maatayim.talklet.screens.mainactivity.childinfo.generaltab;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sophie on 7/6/2017
 */

public class DaysWordsObj implements Parcelable {

    private String word;
    private String usage;
    private String topic;
    private List<String> infoList = new ArrayList<>();
    private List<String> questionList = new ArrayList<>();
    private List<String> activitesList = new ArrayList<>();
    private List<String> ourFaveList = new ArrayList<>();


    public DaysWordsObj(String word, String usage, String topic) {
        this.word = word;
        this.usage = usage;
        this.topic = topic;

        infoList.add("41% of 18 month olds use this word");

        questionList.add("41% of 18 month olds use this word");
        questionList.add("41% of 18 month olds use this word");

        activitesList.add("41% of 18 month olds use this word");
        activitesList.add("41% of 18 month olds use this word");
        activitesList.add("41% of 18 month olds use this word");

        ourFaveList.add("41% of 18 month olds use this word");
        ourFaveList.add("41% of 18 month olds use this word");
        ourFaveList.add("41% of 18 month olds use this word");


    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getTopic() {
        return topic;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }


    public List<String> getInfoList() {
        return infoList;
    }

    public List<String> getQuestionList() {
        return questionList;
    }

    public List<String> getActivitesList() {
        return activitesList;
    }

    public List<String> getOurFaveList() {
        return ourFaveList;
    }
}
