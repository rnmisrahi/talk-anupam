package com.maatayim.talklet.repository.retrofit.model.general;

/**
 * Created by Sophie on 5/28/2017
 */

public class Tip {

    private String id;
    private String childId;
    private String text;
    private String type;

    public Tip(String id, String text, String type, String childId){

        this.id = id;
        this.text = text;
        this.type = type;
        this.childId = childId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
