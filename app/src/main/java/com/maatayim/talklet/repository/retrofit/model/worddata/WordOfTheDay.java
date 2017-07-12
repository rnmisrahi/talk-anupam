package com.maatayim.talklet.repository.retrofit.model.worddata;

/**
 * Created by Sophie on 7/9/2017
 */

public class WordOfTheDay {


    private String word;
    private String subText;
    private String topic;
    private String childId;
    private String id;


    public WordOfTheDay(String word, String subtext, String topic, String childId, String id) {
        this.word = word;
        this.subText = subtext;
        this.topic = topic;
        this.childId = childId;
        this.id = id;
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

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
