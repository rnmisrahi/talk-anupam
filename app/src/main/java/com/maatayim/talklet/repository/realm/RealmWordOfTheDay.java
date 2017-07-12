package com.maatayim.talklet.repository.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sophie on 7/9/2017
 */

public class RealmWordOfTheDay extends RealmObject {

    @PrimaryKey
    private String id;

    private String word;
    private String subtext;
    private String childId;
    private String topic;

    public RealmWordOfTheDay(){

    }

    public RealmWordOfTheDay(String id, String word, String subtext, String childId, String topic) {
        this.id = id;
        this.word = word;
        this.subtext = subtext;
        this.childId = childId;
        this.topic = topic;
    }

    public RealmWordOfTheDay(RealmWordOfTheDay wordOfTheDay) {
        this.word = wordOfTheDay.getWord();
        this.subtext = wordOfTheDay.getSubtext();
        this.childId = wordOfTheDay.getChildId();
        this.topic = wordOfTheDay.getTopic();
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

    public String getChildId() {
        return childId;
    }

    public String getTopic() {
        return topic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
