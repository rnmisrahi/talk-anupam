package com.maatayim.talklet.repository.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sophie on 7/4/2017.
 */

public class RealmTip extends RealmObject {

    @PrimaryKey
    private String id;

    private String tipText;
    private boolean assertion;
    private String childID;

    public RealmTip() {
    }

    public RealmTip(String id, String tipText, boolean assertion, String childID) {
        this.id = id;
        this.tipText = tipText;
        this.assertion = assertion;
        this.childID = childID;
    }

    public String getId() {
        return id;
    }

    public RealmTip(RealmTip copy) {
        this.id = copy.getId();
        this.tipText = copy.getTipText();
        this.assertion = copy.isAssertion();
        this.childID = copy.getChildID();
    }


    public String getTipText() {
        return tipText;
    }

    public void setTipText(String tipText) {
        this.tipText = tipText;
    }

    public boolean isAssertion() {
        return assertion;
    }

    public void setAssertion(boolean assertion) {
        this.assertion = assertion;
    }

    public String getChildID() {
        return childID;
    }

    public void setChildID(String childID) {
        this.childID = childID;
    }
}
