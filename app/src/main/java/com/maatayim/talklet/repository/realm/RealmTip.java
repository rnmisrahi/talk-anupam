package com.maatayim.talklet.repository.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sophie on 7/4/2017
 */

public class RealmTip extends RealmObject {

    @PrimaryKey
    private String id;

    private String tipText;
    private boolean assertion;
    private String childId;

    public RealmTip() {
    }

    public RealmTip(String id, String tipText, boolean assertion, String childID) {
        this.id = id;
        this.tipText = tipText;
        this.assertion = assertion;
        this.childId = childID;
    }

    public String getId() {
        return id;
    }

    public RealmTip(RealmTip copy) {
        this.id = copy.getId();
        this.tipText = copy.getTipText();
        this.assertion = copy.isAssertion();
        this.childId = copy.getChildId();
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

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }
}
