package com.maatayim.talklet.repository.retrofit.model.general;

/**
 * Created by Sophie on 5/28/2017
 */

public class Tip {

    private String id;
    private String childId;
    private String text;
    private boolean assertion;

    public Tip(String id, String text, boolean assertion, String childId){

        this.id = id;
        this.text = text;
        this.assertion = assertion;
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

    public boolean isAssertion() {
        return assertion;
    }

    public void setAssertion(boolean assertion) {
        this.assertion = assertion;
    }
}
