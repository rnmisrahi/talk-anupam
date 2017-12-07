package com.maatayim.talklet.repository.retrofit.model.worddata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sophie on 7/9/2017
 */

public class WordOfTheDay {


    private int id;
    private String word;
    private String subText;
    private String topic;
    private int childId;

    private List<String> infoList = new ArrayList<>();
    private List<String> questionList = new ArrayList<>();
    private List<String> activitiesList = new ArrayList<>();
    private List<String> ourFaveList = new ArrayList<>();


    public WordOfTheDay(String word, String subtext, String topic, int childId, int id,
                        List<String> infoList, List<String> questionList,
                        List<String> activitiesList, List<String> ourFaveList) {
        this.word = word;
        this.subText = subtext;
        this.topic = topic;
        this.childId = childId;
        this.id = id;

        this.infoList = infoList;
        this.questionList = questionList;
        this.activitiesList = activitiesList;
        this.ourFaveList = ourFaveList;
    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<String> infoList) {
        this.infoList = infoList;
    }

    public List<String> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<String> questionList) {
        this.questionList = questionList;
    }

    public List<String> getActivitiesList() {
        return activitiesList;
    }

    public void setActivitiesList(List<String> activitesList) {
        this.activitiesList = activitesList;
    }

    public List<String> getOurFaveList() {
        return ourFaveList;
    }

    public void setOurFaveList(List<String> ourFaveList) {
        this.ourFaveList = ourFaveList;
    }
}
