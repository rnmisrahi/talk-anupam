package com.maatayim.talklet.repository.retrofit.model.general;

/**
 * Created by Sophie on 5/28/2017
 */

public class Tip {

    private int id;
    private int childId;
    private String text;
    private String type;

    public Tip(int id, String text, String type, int childId){

        this.id = id;
        this.text = text;
        this.type = type;
        this.childId = childId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
