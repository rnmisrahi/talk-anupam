package com.maatayim.talklet.repository.realm;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sophie on 7/9/2017
 */

public class RealmWordOfTheDay extends RealmObject {

    @PrimaryKey
    private int id;
    private String word;
    private String subtext;
    private int childId;
    private String topic;

    private String infoList;
    private String questionList;
    private String activitiesList;
    private String ourFaveList;

    public RealmWordOfTheDay(){

    }

    public RealmWordOfTheDay(int id, String word, String subtext, int childId, String topic,
                             List<String> infoList, List<String> questionList, List<String> activitesList,
                             List<String> ourFaveList) {
        this.id = id;
        this.word = word;
        this.subtext = subtext;
        this.childId = childId;
        this.topic = topic;
        setInfoList(infoList);
        setQuestionList(questionList);
        setActivitiesList(activitesList);
        setOurFaveList(ourFaveList);
    }

    public RealmWordOfTheDay(RealmWordOfTheDay wordOfTheDay) {
        this.word = wordOfTheDay.getWord();
        this.subtext = wordOfTheDay.getSubtext();
        this.childId = wordOfTheDay.getChildId();
        this.topic = wordOfTheDay.getTopic();

        setInfoList(wordOfTheDay.getInfoList());
        setQuestionList(wordOfTheDay.getQuestionList());
        setActivitiesList(wordOfTheDay.getActivitiesList());
        setOurFaveList(wordOfTheDay.getOurFaveList());
    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public int getChildId() {
        return childId;
    }

    public String getTopic() {
        return topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


    public List<String> getInfoList() {
        Gson gson = new Gson();
        return gson.fromJson(infoList,new TypeToken<List<String>>(){}.getType());
    }


    public List<String> getQuestionList() {
        Gson gson = new Gson();
        return gson.fromJson(questionList,new TypeToken<List<String>>(){}.getType());
    }

    public List<String> getActivitiesList() {
        Gson gson = new Gson();
        return gson.fromJson(activitiesList,new TypeToken<List<String>>(){}.getType());
    }

    public List<String> getOurFaveList() {
        Gson gson = new Gson();
        return gson.fromJson(ourFaveList,new TypeToken<List<String>>(){}.getType());
    }

    public void setInfoList(List<String> infoList) {
        Gson gson = new Gson();
        this.infoList = gson.toJson(infoList,new TypeToken<List<String>>(){}.getType());
    }

    public void setQuestionList(List<String> questionList) {
        Gson gson = new Gson();
        this.questionList = gson.toJson(questionList,new TypeToken<List<String>>(){}.getType());
    }

    public void setActivitiesList(List<String> activitiesList) {
        Gson gson = new Gson();
        this.activitiesList = gson.toJson(activitiesList,new TypeToken<List<String>>(){}.getType());
    }

    public void setOurFaveList(List<String> ourFaveList) {
        Gson gson = new Gson();
        this.ourFaveList = gson.toJson(ourFaveList,new TypeToken<List<String>>(){}.getType());
    }


}
